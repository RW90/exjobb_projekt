package com.github.rw90.exjobb.MapApp.integration;

import com.github.rw90.exjobb.MapApp.model.AccessLogLine;
import com.opencsv.exceptions.CsvException;
import reactor.core.publisher.Flux;

import java.io.IOException;

public class AccessLogParser {

    private static final int SERVICE_NAME_FIELD = 1;

    public static Flux<AccessLogLine> accessLogLineFlux(AccessLogFileReader reader) throws IOException, CsvException {
        return reader
                .readAll()
                .map(line -> new AccessLogLine(null, null, line[SERVICE_NAME_FIELD], null));
    }
}