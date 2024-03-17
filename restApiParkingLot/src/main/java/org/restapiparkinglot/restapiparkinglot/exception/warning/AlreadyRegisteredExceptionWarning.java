package org.restapiparkinglot.restapiparkinglot.exception.warning;


import java.time.LocalDateTime;

public class AlreadyRegisteredExceptionWarning extends ExceptionWarning {
    public AlreadyRegisteredExceptionWarning(String warning, int status, LocalDateTime timestamp) {
        super(warning, status, timestamp);
    }
}
