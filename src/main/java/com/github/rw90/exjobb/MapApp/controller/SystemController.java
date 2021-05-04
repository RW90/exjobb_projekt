package com.github.rw90.exjobb.MapApp.controller;

import com.github.rw90.exjobb.MapApp.model.SystemOverview;
import com.github.rw90.exjobb.MapApp.service.SystemOverviewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RestController
@RequestMapping("/system")
public class SystemController {

    private final SystemOverviewService systemOverviewService;

    public SystemController(SystemOverviewService systemOverviewService) {
        this.systemOverviewService = systemOverviewService;
    }

    @GetMapping
    public Mono<SystemOverview> getSystem() throws IOException {
        return systemOverviewService.getSystem();
    }
}
