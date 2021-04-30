package com.github.rw90.exjobb.MapApp.controller;

import com.github.rw90.exjobb.MapApp.service.AccessLogService;
import com.github.rw90.exjobb.MapApp.service.LogService;
import com.opencsv.exceptions.CsvException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.IOException;

@RestController
@RequestMapping(value = "/logstream")
public class LogController {

    private final LogService logService;
    private final AccessLogService accessLogService;

    public LogController(LogService logService, AccessLogService accessLogService) {
        this.logService = logService;
        this.accessLogService = accessLogService;
    }

    @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> getLogLines() {
        return logService.getLogLines();
    }

    @GetMapping("/sout")
    public String sout() throws IOException, CsvException {
        accessLogService.getAccessLogLines().distinct().subscribe(System.out::println);
        return "hello logs";
    }
}
