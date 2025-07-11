package org.maravill.foro_hub.security.models;

import org.maravill.foro_hub.security.exception.SecurityDataNotFoundException;

public enum HttpMethod {
    GET, POST, PUT, DELETE, PATCH, HEAD, OPTIONS, TRACE;

    public static HttpMethod getHttpMethod(String method) {
        if (method == null) {
            throw new SecurityDataNotFoundException("HTTP method cannot be null");
        }

        try {
            return HttpMethod.valueOf(method.toUpperCase());
        } catch (Exception _) {
            throw new SecurityDataNotFoundException("Invalid HTTP method: " + method);
        }
    }
}
