package com.github.rw90.exjobb.MapApp.service;

import com.github.rw90.exjobb.MapApp.integration.repository.SystemOverviewRepository;
import com.github.rw90.exjobb.MapApp.model.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

@Service
public class SystemOverviewService {

    private final SystemOverviewRepository systemOverviewRepository;
    private final AccessLogService service;

    public SystemOverviewService(AccessLogService service, SystemOverviewRepository systemOverviewRepository) {
        this.service = service;
        this.systemOverviewRepository = systemOverviewRepository;
    }

    public Flux<SystemOverviewWrapper> getStream() throws IOException {
        SystemOverview system = new SystemOverview();
        systemOverviewRepository.deleteAll().subscribe();

        return service.getAccessLogLines()
                .flatMap(logLine -> detectNewMicroservice(system, logLine))
                .flatMap(wrapper -> detectNewEndpoint(system, wrapper))
                .filter(wrapper -> wrapper.eventHasOccured)
                .flatMap(this::saveAndReturn);
    }

    private Flux<ChangeEventWrapper> detectNewMicroservice(SystemOverview system, AccessLogLine logLine) {
        ChangeEventWrapper wrapper = new ChangeEventWrapper(logLine);
        if (system.addService(new Microservice(logLine.getServiceName()))) {
            wrapper.addOverview(new SystemOverviewWrapper(new SystemOverview(system), "New microservice: " + logLine.getServiceName()));
        }
        return Flux.just(wrapper);
    }

    private Flux<ChangeEventWrapper> detectNewEndpoint(SystemOverview system, ChangeEventWrapper wrapper) {
        Microservice service = system.getServiceByName(wrapper.logLine.getServiceName());
        if (service.addEndpoint(new ApiEndpoint(wrapper.logLine.getMethod(), wrapper.logLine.getEndpoint()))) {
            wrapper.addOverview(
                    new SystemOverviewWrapper(
                            new SystemOverview(system), "New endpoint on " + wrapper.logLine.getServiceName() + ": " + wrapper.logLine.getMethod() + " " + wrapper.logLine.getEndpoint()));
        }
        return Flux.just(wrapper);
    }

    private Flux<SystemOverviewWrapper> saveAndReturn(ChangeEventWrapper wrapper) {
        wrapper.getOverviews().forEach(overview -> systemOverviewRepository.save(overview).subscribe());
        return Flux.fromStream(wrapper.getOverviews());
    }

    private class ChangeEventWrapper {
        private AccessLogLine logLine;
        private List<SystemOverviewWrapper> overviews;
        private boolean eventHasOccured;

        private ChangeEventWrapper(AccessLogLine logLine) {
            this.logLine = logLine;
            overviews = new LinkedList<>();
        }

        private Stream<SystemOverviewWrapper> getOverviews() {
            return overviews.stream();
        }

        private void addOverview(SystemOverviewWrapper overview) {
            overviews.add(overview);
            eventHasOccured = true;
        }
    }
}
