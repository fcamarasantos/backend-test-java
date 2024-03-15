package org.restapiparkinglot.restapiparkinglot.exception;

public class AlreadyRegisteredException extends RuntimeException{
    public AlreadyRegisteredException(String warning){
        super(warning);
    }
}
