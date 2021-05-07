package com.github.rw90.exjobb.MapApp.controller;

import com.github.rw90.exjobb.MapApp.model.SystemOverviewWrapper;
import com.github.rw90.exjobb.MapApp.service.SystemOverviewService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.time.Duration;

@RestController
public class StreamController {

    private final SystemOverviewService service;

    public StreamController(SystemOverviewService service) {
        this.service = service;
    }

    @GetMapping(value = "/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<SystemOverviewWrapper> getStream() throws IOException {
        return service.getStream().delayElements(Duration.ofSeconds(3));
    }
}
