package com.github.rw90.exjobb.MapApp.integration;

import com.github.rw90.exjobb.MapApp.model.ServiceLogLine;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Component
public class ServiceLogParser implements LogParser<ServiceLogLine> {

    private final LogFileReader<String> reader;

    public ServiceLogParser(@Qualifier("serviceLogFileReader") LogFileReader<String> reader) {
        this.reader = reader;
    }

    @Override
    public Flux<ServiceLogLine> parseLines() throws IOException {
        return reader
                .readAllLines()
                .map(this::logEntryToServiceLogLine);
    }

    private ServiceLogLine logEntryToServiceLogLine(String logEntry) {
        String serviceName = extractServiceName(logEntry);
        String serviceVersion = extractServiceVersion(logEntry);
        return new ServiceLogLine(serviceName, serviceVersion);
    }

    private String extractServiceName(String logEntry) {
        return StringUtils.substringBetween(logEntry, "appName=", ",");
    }

    private String extractServiceVersion(String logEntry) {
        return StringUtils.substringBetween(logEntry, "appVersion=", ",");
    }
}
