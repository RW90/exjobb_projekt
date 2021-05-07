package com.github.rw90.exjobb.MapApp.model;

import java.util.HashSet;
import java.util.Set;

public class Microservice {

    private final String name;
    private Set<ApiEndpoint> endpoints;

    public Microservice(String name) {
        this.name = name;
        this.endpoints = new HashSet<>();
    }

    public static Microservice copyOf(Microservice service) {
        Microservice copy = new Microservice(service.getName());
        service.getEndpoints().forEach(endpoint -> copy.addEndpoint(ApiEndpoint.copyOf(endpoint)));
        return copy;
    }

    public boolean addEndpoint(ApiEndpoint endpoint) {
        return endpoints.add(endpoint);
    }

    public Set<ApiEndpoint> getEndpoints() {
        return endpoints;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Microservice that = (Microservice) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
