package com.github.rw90.exjobb.MapApp.model;

import org.springframework.http.HttpMethod;

import java.util.Objects;
import java.util.UUID;

public class AccessLogLine {

    private HttpMethod method;
    private String endpoint;
    private String serviceName;
    private UUID traceId;

    public AccessLogLine(HttpMethod method, String endpoint, String serviceName, UUID traceId) {
        this.method = method;
        this.endpoint = endpoint;
        this.serviceName = serviceName;
        this.traceId = traceId;
    }

    @Override
    public String toString() {
        return "AccessLogLine{" +
                "method=" + method +
                ", endpoint='" + endpoint + '\'' +
                ", serviceName='" + serviceName + '\'' +
                ", traceId=" + traceId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccessLogLine that = (AccessLogLine) o;
        return method == that.method && Objects.equals(endpoint, that.endpoint) && Objects.equals(serviceName, that.serviceName) && Objects.equals(traceId, that.traceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, endpoint, serviceName, traceId);
    }

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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
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
