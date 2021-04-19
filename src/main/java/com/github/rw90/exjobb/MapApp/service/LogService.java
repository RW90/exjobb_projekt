package com.github.rw90.exjobb.MapApp.service;

import com.github.rw90.exjobb.MapApp.integration.LogReader;
import com.github.rw90.exjobb.MapApp.util.LogParser;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class LogService {

    private final LogReader logReader;

    public LogService(LogReader logReader) {
        this.logReader = logReader;
    }

    public Flux<String> getLogLines() {
        return LogParser.parseSystemNamesFromLogs(logReader.readAllLines());
    }
}
