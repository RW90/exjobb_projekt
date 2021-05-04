package com.github.rw90.exjobb.MapApp.service;

import com.github.rw90.exjobb.MapApp.integration.CsvLogFileReader;
import com.github.rw90.exjobb.MapApp.integration.LogParser;
import com.github.rw90.exjobb.MapApp.model.AccessLogLine;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Service
public class AccessLogService {

    private final CsvLogFileReader reader;
    private final LogParser<AccessLogLine> parser;

    public AccessLogService(@Qualifier("accessLogFileReader") CsvLogFileReader reader, LogParser<AccessLogLine> accessLogParser) {
        this.reader = reader;
        this.parser = accessLogParser;
    }

    public Flux<AccessLogLine> getAccessLogLines() throws IOException {
        return parser
                .logLinesFlux(reader);
    }
}
