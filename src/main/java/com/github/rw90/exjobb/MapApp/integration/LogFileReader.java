package com.github.rw90.exjobb.MapApp.integration;

import reactor.core.publisher.Flux;

import java.io.IOException;

public interface LogFileReader<T> {

    Flux<T> readAllLines() throws IOException;
}
