package com.github.rw90.exjobb.MapApp.model;

import org.springframework.http.HttpMethod;

public class ApiEndpoint {

    private final HttpMethod method;
    private final String path;

    public ApiEndpoint(HttpMethod method, String path) {
        this.method = method;
        this.path = path;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }
}
