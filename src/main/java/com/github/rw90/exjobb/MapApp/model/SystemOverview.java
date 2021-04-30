package com.github.rw90.exjobb.MapApp.model;

import java.util.ArrayList;
import java.util.List;

public class SystemOverview {

    private List<Microservice> services;
    private List<Dependency> dependencies;

    public SystemOverview() {
        services = new ArrayList<>();
        dependencies = new ArrayList<>();
    }

    public void addService(Microservice service) {
        services.add(service);
    }

    public void addDependency(Dependency dependency) {
        dependencies.add(dependency);
    }

    public List<Microservice> getServices() {
        return services;
    }

    public List<Dependency> getDependencies() {
        return dependencies;
    }
}
