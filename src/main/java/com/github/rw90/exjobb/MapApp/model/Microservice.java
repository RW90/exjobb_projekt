package com.github.rw90.exjobb.MapApp.model;

import java.util.ArrayList;
import java.util.List;

public class Microservice {

    private List<ApiEndpoint> endpoints;
    private final String name;

    public Microservice(String name) {
        this.name = name;
        this.endpoints = new ArrayList<>();
    }

    public void addEndpoint(ApiEndpoint endpoint) {
        endpoints.add(endpoint);
    }

    public List<ApiEndpoint> getEndpoints() {
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
