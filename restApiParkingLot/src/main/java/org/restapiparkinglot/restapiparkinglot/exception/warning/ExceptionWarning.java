package org.restapiparkinglot.restapiparkinglot.exception.warning;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
public class ExceptionWarning {
    private String warning;
    private int status;
    private LocalDateTime timestamp;


}