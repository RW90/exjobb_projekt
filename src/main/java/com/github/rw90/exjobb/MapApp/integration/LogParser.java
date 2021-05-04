package com.github.rw90.exjobb.MapApp.integration;

import reactor.core.publisher.Flux;

import java.io.IOException;

/**
 * Methods to parse log entries to a specific type.
 * @param <T>
 */
public interface LogParser<T> {

    /**
     * Parses logs from any underlying source and returns a Flux.
     * @return A Flux of the lines in the underlying logs
     * @throws IOException
     */
    Flux<T> parseLines() throws IOException;
}
