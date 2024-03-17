package org.restapiparkinglot.restapiparkinglot.handler;

import org.restapiparkinglot.restapiparkinglot.exception.AlreadyRegisteredException;
import org.restapiparkinglot.restapiparkinglot.exception.NotFoundException;
import org.restapiparkinglot.restapiparkinglot.exception.ParkingLotFullException;
import org.restapiparkinglot.restapiparkinglot.exception.warning.AlreadyRegisteredExceptionWarning;
import org.restapiparkinglot.restapiparkinglot.exception.warning.NotFoundExceptionWarning;
import org.restapiparkinglot.restapiparkinglot.exception.warning.ParkingLotFullExceptionWarning;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionWarning> handleNotFound(NotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new NotFoundExceptionWarning(e.getMessage(), HttpStatus.NOT_FOUND.value(), LocalDateTime.now()));

    }
    @ExceptionHandler(AlreadyRegisteredException.class)
    public ResponseEntity<AlreadyRegisteredExceptionWarning> handleAlreadyExists(AlreadyRegisteredException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AlreadyRegisteredExceptionWarning(e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()));
    }
    @ExceptionHandler(ParkingLotFullException.class)
    public ResponseEntity<ParkingLotFullExceptionWarning> handleAlreadyFull(ParkingLotFullException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ParkingLotFullExceptionWarning(e.getMessage(), HttpStatus.BAD_REQUEST.value(), LocalDateTime.now()));
    }

}
