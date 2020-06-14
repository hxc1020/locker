package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketNotValidException;

import java.util.List;

public class Robot {
    private final List<Locker> lockers;

    public Robot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket save(Bag bag) throws LockerIsFullException {
        if (lockers.get(0).isFull()) {
            return lockers.get(1).save(bag);
        }
        return lockers.get(0).save(bag);
    }

    public Bag take(Ticket ticket) throws TicketNotValidException {
        for (Locker locker : lockers) {
            if (locker.hasBag(ticket)) {
                return locker.take(ticket);
            }
        }
        throw new TicketNotValidException("Ticket invalid");
    }
}
