package com.ice.songsapp.web.exceptions;

import java.util.Map;
import java.util.Objects;
import org.springframework.http.HttpStatusCode;

public class ApiException extends RuntimeException {
    private final HttpStatusCode status;
    private final String failureReason;
    private final Map<String, Object> details;

    public ApiException(HttpStatusCode status, String failureReason) {
        this(status, failureReason, Map.of());
    }

    public ApiException(HttpStatusCode status, String failureReason, Map<String, ?> details) {
        super(failureReason);
        this.status = (HttpStatusCode) Objects.requireNonNull(status);
        this.failureReason = (String)Objects.requireNonNull(failureReason);
        this.details = Map.copyOf((Map)Objects.requireNonNull(details));
    }

    public HttpStatusCode getStatus() {
        return this.status;
    }

    public String getFailureReason() {
        return this.failureReason;
    }

    public Map<String, Object> getDetails() {
        return this.details;
    }
}
