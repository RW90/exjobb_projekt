package com.github.rw90.exjobb.MapApp.controller;

import com.github.rw90.exjobb.MapApp.integration.repository.SystemOverviewRepository;
import com.github.rw90.exjobb.MapApp.model.SystemOverview;
import com.github.rw90.exjobb.MapApp.model.SystemOverviewWrapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@RestController
public class SnapshotController {

    private final SystemOverviewRepository repository;

    public SnapshotController(SystemOverviewRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/snapshots")
    public Flux<SystemOverviewWrapper> getSnapshots() {
        return repository.findAll().sort();
    }

    @GetMapping("/snapshot")
    public Mono<SystemOverviewWrapper> getSnapshot() {
        return Mono.from(repository.findAll().sort().takeLast(1));
    }

    @GetMapping("/snapshot/{id}")
    public Mono<SystemOverviewWrapper> getSnapshotById(@PathVariable String id) {
        return repository.findById(UUID.fromString(id));
    }

    @GetMapping("/snapshot/dummy")
    public Mono<SystemOverviewWrapper> getDummySnapshot() {
        return Mono.just(new SystemOverviewWrapper(new SystemOverview()));
    }
}
