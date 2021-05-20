package com.github.rw90.exjobb.MapApp.service;

import com.github.rw90.exjobb.MapApp.integration.repository.SystemOverviewRepository;
import com.github.rw90.exjobb.MapApp.model.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
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
        TraceMap tracemap = new TraceMap();
        SystemOverview system = new SystemOverview();
        systemOverviewRepository.deleteAll().subscribe();

        return service.getAccessLogLines()
                .flatMap(logLine -> {
                  /*  ChangeEventWrapper wrapper = new ChangeEventWrapper();
                    if (detectNewMicroservice(system, logLine)) {
                        wrapper.addOverview(new SystemOverviewWrapper(new SystemOverview(system), "New microservice: " + logLine.getServiceName()));
                    }

                    if (detectNewEndpoint(system, logLine)) {
                        wrapper.addOverview(
                                new SystemOverviewWrapper(
                                        new SystemOverview(system), "New endpoint on " + logLine.getServiceName() + ": " + logLine.getMethod() + " " + logLine.getEndpoint()));
                    }

                    if (detectNewTrace(system, logLine, tracemap)) {
                        wrapper.addOverview(
                                new SystemOverviewWrapper(
                                        new SystemOverview(system), "New dependency added"
                                )
                        );
                    }*/
                    ChangeEventWrapper wrapper = new ChangeEventWrapper();
                    boolean a = detectNewMicroservice(system, logLine);
                    boolean b = detectNewEndpoint(system, logLine);
                    boolean c = detectNewTrace(system, logLine, tracemap);

                    if (a || b || c) {
                        wrapper.addOverview((
                                new SystemOverviewWrapper(
                                        new SystemOverview(system), "new changes"
                                )
                        ));
                    }

                    return Flux.just(wrapper);
                })
                .filter(wrapper -> wrapper.eventHasOccured)
                .flatMap(this::saveAndReturn);
    }

    private boolean detectNewMicroservice(SystemOverview system, AccessLogLine logLine) {
        return system.addService(new Microservice(logLine.getServiceName()));
    }

    private boolean detectNewEndpoint(SystemOverview system, AccessLogLine logLine) {
        Microservice service = system.getServiceByName(logLine.getServiceName());
        return service.addEndpoint(new ApiEndpoint(logLine.getMethod(), logLine.getEndpoint()));
    }

    private boolean detectNewTrace(SystemOverview system, AccessLogLine logLine, TraceMap traceMap) {
        if(traceMap.traceExists(logLine.getTraceId())) {
            traceMap.insertService(logLine.getTraceId(), logLine.getServiceName(), logLine.getTimestamp());
        } else {
            Optional<Trace> traceToAdd = traceMap.getOldestTrace();
            traceMap.addNewTrace(logLine.getTraceId(), logLine.getServiceName(), logLine.getTimestamp());
            Optional<List<Dependency>> dependencies = Optional.empty();
            if(traceToAdd.isPresent()) {
                dependencies = traceToAdd.get().getDependencies();
            }
            if(dependencies.isPresent() && system.addDependencies(dependencies.get())) {
                return true;
            }
        }
        return false;
    }

    private Flux<SystemOverviewWrapper> saveAndReturn(ChangeEventWrapper wrapper) {
        wrapper.getOverviews().forEach(overview -> systemOverviewRepository.save(overview).subscribe());
        return Flux.fromStream(wrapper.getOverviews());
    }

    private class ChangeEventWrapper {
        private List<SystemOverviewWrapper> overviews;
        private boolean eventHasOccured;

        private ChangeEventWrapper() {
            overviews = new LinkedList<>();
        }

        private Stream<SystemOverviewWrapper> getOverviews() {
//            overviews.forEach(ov -> {
//                System.out.println("------");
//                ov.getSystemOverview().getDependencies().forEach(System.out::println);
//            });
            return overviews.stream();
        }

        private void addOverview(SystemOverviewWrapper overview) {
            overviews.add(overview);
            eventHasOccured = true;
        }

    }
}
