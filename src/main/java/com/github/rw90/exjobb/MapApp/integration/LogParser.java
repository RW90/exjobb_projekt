package com.github.rw90.exjobb.MapApp.integration;

import reactor.core.publisher.Flux;

import java.io.IOException;

/**
 * Methods to parse log entries to a specific type.
 * @param <T>
 */
public interface LogParser<T> {

    /**
     * Takes a CsvLogFileReader and reads the underlying .csv log file. Returns a Flux
     * of the entries in the log file.
     * @param reader The CsvLogFileReader used to read a log file
     * @return A Flux of the lines in the log file
     * @throws IOException
     */
    Flux<T> logLinesFlux(CsvLogFileReader reader) throws IOException;
}
