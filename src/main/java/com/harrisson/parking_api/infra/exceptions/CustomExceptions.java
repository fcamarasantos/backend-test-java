// src/main/java/com/harrisson/parking_api/infra/exceptions/CustomExceptions.java
package com.harrisson.parking_api.infra.exceptions;

public class CustomExceptions {

    public static class ResourceNotFoundException extends RuntimeException {
        public ResourceNotFoundException(String message) {
            super(message);
        }
    }

    public static class InvalidRequestException extends RuntimeException {
        public InvalidRequestException(String message) {
            super(message);
        }
    }

    public static class PaymentRequiredException extends RuntimeException {
        public PaymentRequiredException(String message) {
            super(message);
        }
    }

    public static class InternalServerErrorException extends RuntimeException {
        public InternalServerErrorException(String message) {
            super(message);
        }
    }
}