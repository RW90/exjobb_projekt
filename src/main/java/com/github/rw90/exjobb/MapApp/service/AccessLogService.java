package com.github.rw90.exjobb.MapApp.service;

import com.github.rw90.exjobb.MapApp.integration.AccessLogParser;
import com.github.rw90.exjobb.MapApp.integration.CsvLogFileReader;
import com.github.rw90.exjobb.MapApp.model.AccessLogLine;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Service
public class AccessLogService {

    private CsvLogFileReader reader;

    public AccessLogService(@Qualifier("accessLogFileReader") CsvLogFileReader reader) {
        this.reader = reader;
    }

    public Flux<AccessLogLine> getAccessLogLines() throws IOException {
        return AccessLogParser
                .accessLogLineFlux(reader);
    }
}
