package com.github.rw90.exjobb.MapApp.model;

import org.springframework.http.HttpMethod;

public class ApiEndpoint {

    private final HttpMethod method;
    private final String path;

    public ApiEndpoint(HttpMethod method, String path) {
        this.method = method;
        this.path = path;
    }

    public static ApiEndpoint copyOf(ApiEndpoint endpoint) {
        return new ApiEndpoint(HttpMethod.resolve(endpoint.getMethod().name()), endpoint.getPath());
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getPath() {
        return path;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApiEndpoint that = (ApiEndpoint) o;

        if (method != that.method) return false;
        return path.equals(that.path);
    }

    @Override
    public int hashCode() {
        int result = method.hashCode();
        result = 31 * result + path.hashCode();
        return result;
    }
}
