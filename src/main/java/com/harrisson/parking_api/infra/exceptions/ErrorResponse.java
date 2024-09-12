// src/main/java/com/harrisson/parking_api/exception/ErrorResponse.java
package com.harrisson.parking_api.infra.exceptions;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp;
    private String message;
    private String details;

    public ErrorResponse(LocalDateTime timestamp, String message, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
    }

    // Getters and setters
}