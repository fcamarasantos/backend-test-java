package org.restapiparkinglot.restapiparkinglot.exception.warning;

import java.time.LocalDateTime;

public class NotFoundExceptionWarning extends ExceptionWarning{
    public NotFoundExceptionWarning (String warning, int status, LocalDateTime timestamp) {
        super(warning, status, timestamp);
    }
}

