package com.github.rw90.exjobb.MapApp.service;

import com.github.rw90.exjobb.MapApp.model.ApiEndpoint;
import com.github.rw90.exjobb.MapApp.model.Microservice;
import com.github.rw90.exjobb.MapApp.model.SystemOverview;

import java.io.IOException;

public class SystemOverviewService {

    private SystemOverview system;
    private final AccessLogService service;

    public SystemOverviewService(AccessLogService service) {
        this.service = service;
        this.system = new SystemOverview();
    }

    public SystemOverview getSystem() throws IOException {
        fetchSystemDataFromLogs();
        return system;
    }

    private void fetchSystemDataFromLogs() throws IOException {
        service.getAccessLogLines()
                .doOnNext(logLine -> {
                    system.addService(new Microservice(logLine.getServiceName()));
                })
                .doOnNext(logLine -> {
                    Microservice microservice = system.getServiceByName(logLine.getServiceName());
                    microservice.addEndpoint(new ApiEndpoint(logLine.getMethod(), logLine.getEndpoint()));
                });
    }
}
