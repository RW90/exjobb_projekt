package com.github.rw90.exjobb.MapApp.integration;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.stream.Stream;

@Component
public class FileLogReader implements LogReader {

    @Override
    public Flux<String> readAllLines() {
        Stream<String> logLines = null;
        try {
            logLines = Files.lines(Path.of("src/main/resources/startedservices.csv"));
        } catch (Exception e) {
            System.err.println(e);
        }

        return Flux.fromStream(logLines).skip(1).delayElements(Duration.ofMillis(1000));
    }
}
