package com.github.rw90.exjobb.MapApp.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Trace {

    private final UUID traceId;
    private LocalDateTime timeOfLastRequest;
    private final List<String> microservices;


    public Trace(UUID traceId, LocalDateTime timeOfRequest, String microservice) {
        this.traceId = traceId;
        this.timeOfLastRequest = timeOfRequest;
        this.microservices = new ArrayList<>();
        this.microservices.add(microservice);
    }

    public void addService(String microservice, LocalDateTime timeOfRequest) {
        microservices.add(microservice);
        this.timeOfLastRequest = timeOfRequest;
    }

    public UUID getTraceId() {
        return traceId;
    }

    public LocalDateTime getTimeOfLastRequest() {
        return timeOfLastRequest;
    }

    public List<String> getMicroservices() {
        return microservices;
    }
}
