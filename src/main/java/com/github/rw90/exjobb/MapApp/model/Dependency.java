package com.github.rw90.exjobb.MapApp.model;

import java.util.UUID;

public class Dependency {

    private final UUID id;
    private Microservice firstService;
    private Microservice secondService;

    public Dependency() {
        this.id = UUID.randomUUID();
    }

    public Dependency(Microservice firstService, Microservice secondService) {
        this();
        this.firstService = firstService;
        this.secondService = secondService;
    }

    public void setFirstService(Microservice firstService) {
        this.firstService = firstService;
    }

    public void setSecondService(Microservice secondService) {
        this.secondService = secondService;
    }

    public UUID getId() {
        return id;
    }

    public Microservice getFirstService() {
        return firstService;
    }

    public Microservice getSecondService() {
        return secondService;
    }
}
