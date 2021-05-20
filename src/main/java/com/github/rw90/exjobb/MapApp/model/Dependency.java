package com.github.rw90.exjobb.MapApp.model;

import java.util.UUID;

public class Dependency {

    private final UUID id;
    private String fromService;
    private String toService;

    public Dependency() {
        this.id = UUID.randomUUID();
    }

    public Dependency(String fromService, String toService) {
        this();
        this.fromService = fromService;
        this.toService = toService;
    }

    public void setFromService(String fromService) {
        this.fromService = fromService;
    }

    public void setToService(String toService) {
        this.toService = toService;
    }

    public UUID getId() {
        return id;
    }

    public String getFromService() {
        return fromService;
    }

    public String getToService() {
        return toService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dependency that = (Dependency) o;

        if (fromService != null ? !fromService.equals(that.fromService) : that.fromService != null) return false;
        return toService != null ? toService.equals(that.toService) : that.toService == null;
    }

    @Override
    public int hashCode() {
        int result = fromService != null ? fromService.hashCode() : 0;
        result = 31 * result + (toService != null ? toService.hashCode() : 0);
        return result;
    }
}
