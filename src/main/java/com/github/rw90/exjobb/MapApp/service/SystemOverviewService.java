package com.github.rw90.exjobb.MapApp.service;

import com.github.rw90.exjobb.MapApp.model.ApiEndpoint;
import com.github.rw90.exjobb.MapApp.model.Microservice;
import com.github.rw90.exjobb.MapApp.model.SystemOverview;
import com.opencsv.exceptions.CsvException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class SystemOverviewService {

    private SystemOverview system;
    private final AccessLogService service;

    public SystemOverviewService(AccessLogService service) {
        this.service = service;
        this.system = new SystemOverview();
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
                    system.addService(new Microservice(logLine.getServiceName()));
                })
                .doOnNext(logLine -> {
                    Microservice microservice = system.getServiceByName(logLine.getServiceName());
                    microservice.addEndpoint(new ApiEndpoint(logLine.getMethod(), logLine.getEndpoint()));
                }).subscribe();
    }


}
