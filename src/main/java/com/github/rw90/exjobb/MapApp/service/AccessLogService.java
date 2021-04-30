package com.github.rw90.exjobb.MapApp.service;

import com.github.rw90.exjobb.MapApp.integration.AccessLogFileReader;
import com.github.rw90.exjobb.MapApp.integration.AccessLogParser;
import com.github.rw90.exjobb.MapApp.model.AccessLogLine;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Path;

@Service
public class AccessLogService {

    private final String PATH_TO_ACCESS_LOG_FILE = "src/main/resources/accesslogs.csv";

    public Flux<AccessLogLine> getAccessLogLines() throws IOException {
        AccessLogFileReader reader = new AccessLogFileReader(Path.of(PATH_TO_ACCESS_LOG_FILE));
        return AccessLogParser
                .accessLogLineFlux(reader);
    }
}
