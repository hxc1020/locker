package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketIsInvalidException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartLockerRobot implements Robot{
    private final List<Locker> lockers;

    public SmartLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket save(Bag bag) throws LockerIsFullException {
        Optional<Locker> maxSpaceLocker = lockers.stream().max(Comparator.comparing(Locker::freeSpace));
        if (maxSpaceLocker.isPresent()) {
            return maxSpaceLocker.get().save(bag);
        }
        return null;
    }

    public Bag take(Ticket ticket) throws TicketIsInvalidException {
        for (Locker locker : lockers) {
            if (locker.hasBag(ticket)) {
                return locker.take(ticket);
            }
        }
        throw new TicketIsInvalidException();
    }
}

