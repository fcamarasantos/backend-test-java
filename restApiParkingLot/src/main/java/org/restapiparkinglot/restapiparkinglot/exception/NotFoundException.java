package org.restapiparkinglot.restapiparkinglot.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException (String warning){
        super(warning);
    }
}
