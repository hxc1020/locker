package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class SmartLockerRobot extends BaseRobot {

    public SmartLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket save(Bag bag) throws LockerIsFullException {
        Optional<Locker> maxSpaceLocker = lockers.stream().max(Comparator.comparing(Locker::freeSpace));
        if (maxSpaceLocker.isPresent()) {
            Ticket ticket = maxSpaceLocker.get().save(bag);
            ticket.setType(TicketType.GIVEN_BY_ROBOT);
            return ticket;
        }
        return null;
    }
}

