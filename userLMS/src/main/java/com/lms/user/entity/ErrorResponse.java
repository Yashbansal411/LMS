package com.lms.user.entity;

import java.util.Date;
import java.util.Map;

public class ErrorResponse {
    private Date timestamp;
    private String message;
    private Map<String, String> details;

    public ErrorResponse(Date timestamp, String message, Map<String, String> details) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getDetails() {
        return details;
    }
}
