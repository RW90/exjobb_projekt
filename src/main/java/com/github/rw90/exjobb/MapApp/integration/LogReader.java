package com.github.rw90.exjobb.MapApp.integration;

import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.stream.Stream;

public interface LogReader {

    public Flux<String> readAllLines();
}
