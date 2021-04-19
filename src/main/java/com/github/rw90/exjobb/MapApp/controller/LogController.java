package com.github.rw90.exjobb.MapApp.controller;

import com.github.rw90.exjobb.MapApp.service.LogService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/logstream")
public class LogController {

    private final LogService logService;

    public LogController(LogService logService) {
        this.logService = logService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getLogLines() {
        return logService.getLogLines();
    }
}
