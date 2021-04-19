package com.github.rw90.exjobb.MapApp.integration;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FileLogReader implements LogReader {

    @Override
    public Flux<String> readAllLines() throws IOException {
        Stream<String> logLines = Files.lines(Path.of("src/main/resources/startedservices.csv"));
        return Flux.fromStream(logLines);
    }
}
