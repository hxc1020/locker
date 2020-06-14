package com.thoughtworks.tdd.exception;

public class TicketIsInvalidException extends Exception {
    public TicketIsInvalidException(String message) {
        super(message);
    }
}
