package com.thoughtworks.tdd.exception;

public class LockerIsFullException extends Exception{
    public LockerIsFullException(String message) {
        super(message);
    }
}
