package org.restapiparkinglot.restapiparkinglot.exception.warning;

import java.time.LocalDateTime;

public class ParkingLotFullExceptionWarning extends ExceptionWarning {
    public ParkingLotFullExceptionWarning(String warning, int status, LocalDateTime timestamp){
        super(warning, status, timestamp);
    }
}
