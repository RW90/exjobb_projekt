package com.github.rw90.exjobb.MapApp.model;

import java.util.HashSet;
import java.util.Set;

public class SystemOverview {

    private Set<Microservice> services;
    private Set<Dependency> dependencies;

    public SystemOverview() {
        services = new HashSet<>();
        dependencies = new HashSet<>();
    }

    public SystemOverview(SystemOverview other) {
        this.services = new HashSet<>();
        other.getServices().forEach(service -> this.services.add(Microservice.copyOf(service)));
        this.dependencies = new HashSet<>();
    }

    public boolean addService(Microservice service) {
        return services.add(service);
    }

    public boolean addDependency(Dependency dependency) {
        return dependencies.add(dependency);
    }

    public Microservice getServiceByName(String name) {
        return services.stream()
                .filter(service -> service.getName().equals(name))
                .findFirst()
                .orElseThrow();

    }

    public Set<Microservice> getServices() {
        return services;
    }

    public Set<Dependency> getDependencies() {
        return dependencies;
    }

    @Override
    public String toString() {
        return "SystemOverview{" +
                "services=" + services +
                ", dependencies=" + dependencies +
                '}';
    }
}
