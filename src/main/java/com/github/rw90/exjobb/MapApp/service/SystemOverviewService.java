package com.github.rw90.exjobb.MapApp.service;

import com.github.rw90.exjobb.MapApp.integration.repository.SystemOverviewRepository;
import com.github.rw90.exjobb.MapApp.model.*;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

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
                    StringBuilder changes = new StringBuilder(logLine.getTimestamp() + ":");
                    ChangeEventWrapper wrapper = new ChangeEventWrapper();

                    boolean hasNewMicroservice = detectNewMicroservice(system, logLine);

                    if (hasNewMicroservice) {
                        changes.append(" " + "Service " + logLine.getServiceName() + " added;");
                    }

                    boolean hasNewEndpoint = detectNewEndpoint(system, logLine);

                    if (hasNewEndpoint) {
                        changes.append(" " + logLine.getMethod() + " " + logLine.getEndpoint() + " added on " + logLine.getServiceName() + ";");
                    }

                    Optional<List<Dependency>> dependencies = detectNewTrace(system, logLine, tracemap);

                    if (dependencies.isPresent()) {
                        dependencies.get().forEach(dependency ->
                            changes.append(" new dependency from " + dependency.getFromService() + " to " + dependency.getToService() + ";")
                        );
                    }

                    if (hasNewMicroservice || hasNewEndpoint || dependencies.isPresent()) {
                        wrapper.addOverview((
                                new SystemOverviewWrapper(
                                        new SystemOverview(system), changes.toString()
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

    private Optional<List<Dependency>> detectNewTrace(SystemOverview system, AccessLogLine logLine, TraceMap traceMap) {
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
                return Optional.of(dependencies.get());
            }
        }
        return Optional.empty();
    }

    private Flux<SystemOverviewWrapper> saveAndReturn(ChangeEventWrapper wrapper) {
        systemOverviewRepository.save(wrapper.getOverview()).subscribe();
        return Flux.just(wrapper.getOverview());
    }

    private class ChangeEventWrapper {
        SystemOverviewWrapper overview;
        private boolean eventHasOccured;

        private SystemOverviewWrapper getOverview() {
            return overview;
        }

        private void addOverview(SystemOverviewWrapper overview) {
            this.overview = overview;
            eventHasOccured = true;
        }

    }
}
