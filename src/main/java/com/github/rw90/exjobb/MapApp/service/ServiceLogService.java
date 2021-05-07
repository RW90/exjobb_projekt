package com.github.rw90.exjobb.MapApp.service;

import com.github.rw90.exjobb.MapApp.integration.LogParser;
import com.github.rw90.exjobb.MapApp.model.ServiceLogLine;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;

@Service
public class ServiceLogService {

    private final LogParser<ServiceLogLine> parser;

    public ServiceLogService(LogParser<ServiceLogLine> serviceLogParser) {
        this.parser = serviceLogParser;
    }

    public Flux<ServiceLogLine> getAccessLogLines() throws IOException {
        return parser
                .parseLines();
    }
}
