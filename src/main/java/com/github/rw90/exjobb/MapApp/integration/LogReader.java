package com.github.rw90.exjobb.MapApp.integration;

import reactor.core.publisher.Flux;

import java.io.IOException;

public interface LogReader {

    public Flux<String> readAllLines() throws IOException;
}
