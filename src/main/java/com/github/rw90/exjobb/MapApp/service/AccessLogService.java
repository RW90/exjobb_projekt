package com.github.rw90.exjobb.MapApp.service;

import com.github.rw90.exjobb.MapApp.integration.LogParser;
import com.github.rw90.exjobb.MapApp.model.AccessLogLine;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Service
public class AccessLogService {

    private final LogParser<AccessLogLine> parser;

    public AccessLogService(LogParser<AccessLogLine> accessLogParser) {
        this.parser = accessLogParser;
    }

    public Flux<AccessLogLine> getAccessLogLines() throws IOException {
        return parser
                .parseLines();
    }
}
