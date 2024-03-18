package org.restapiparkinglot.restapiparkinglot.exception;

public class ParkingLotFullException extends RuntimeException{
    public ParkingLotFullException (String warning) {
        super(warning);
        
    }
}
