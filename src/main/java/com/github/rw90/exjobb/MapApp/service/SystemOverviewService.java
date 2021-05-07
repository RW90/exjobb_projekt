package com.github.rw90.exjobb.MapApp.service;

import com.github.rw90.exjobb.MapApp.integration.repository.SystemOverviewRepository;
import com.github.rw90.exjobb.MapApp.model.ApiEndpoint;
import com.github.rw90.exjobb.MapApp.model.Microservice;
import com.github.rw90.exjobb.MapApp.model.SystemOverview;
import com.github.rw90.exjobb.MapApp.model.SystemOverviewWrapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class SystemOverviewService {

    private SystemOverview system;
    private final SystemOverviewRepository systemOverviewRepository;
    private final AccessLogService service;

    public SystemOverviewService(AccessLogService service, SystemOverviewRepository systemOverviewRepository) {
        this.service = service;
        this.system = new SystemOverview();
        this.systemOverviewRepository = systemOverviewRepository;
        try {
            fetchSystemDataFromLogs();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Mono<SystemOverview> getSystem() throws IOException {
        //fetchSystemDataFromLogs();
        return Mono.just(system);
    }

    private void fetchSystemDataFromLogs() throws IOException {
        service.getAccessLogLines()
                .doOnNext(logLine -> {
                    boolean a = system.addService(new Microservice(logLine.getServiceName()));
                    if (a) systemOverviewRepository.save(new SystemOverviewWrapper(system)).subscribe();
                })
                .doOnNext(logLine -> {
                    Microservice microservice = system.getServiceByName(logLine.getServiceName());
                    boolean b = microservice.addEndpoint(new ApiEndpoint(logLine.getMethod(), logLine.getEndpoint()));
                    if (b) systemOverviewRepository.save(new SystemOverviewWrapper(system)).subscribe();
                }).subscribe();
    }


}
