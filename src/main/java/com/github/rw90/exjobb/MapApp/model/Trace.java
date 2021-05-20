package com.github.rw90.exjobb.MapApp.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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

    public Optional<List<Dependency>> getDependencies() {
        int nrOfMicroservices = microservices.size();

        if(nrOfMicroservices == 1) {
            return Optional.empty();
        }

        List<Dependency> returnList = new ArrayList<>();
        for(int i = 1; i < nrOfMicroservices; i++) {
            String fromService = microservices.get(i - 1);
            String toService = microservices.get(i);
            Dependency dependency = new Dependency(fromService, toService);
            returnList.add(dependency);
        }

        return Optional.of(returnList);
    }
}
