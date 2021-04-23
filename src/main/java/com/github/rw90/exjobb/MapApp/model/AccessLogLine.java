package com.github.rw90.exjobb.MapApp.model;

import org.springframework.http.HttpMethod;

import java.util.UUID;

public class AccessLogLine {

    private HttpMethod method;
    private String endpoint;
    private String name;
    private UUID traceId;

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getTraceId() {
        return traceId;
    }

    public void setTraceId(UUID traceId) {
        this.traceId = traceId;
    }

    public void setTraceId(String traceId) {
        this.traceId = UUID.fromString(traceId);
    }
}
