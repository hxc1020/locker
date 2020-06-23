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
        Optional<Locker> locker = getAvailableLocker();
        if (locker.isPresent()) {
            Ticket ticket = locker.get().save(bag);
            ticket.setType(TicketType.GIVEN_BY_ROBOT);
            return ticket;
        }
        throw new LockerIsFullException();
    }

    @Override
    public Optional<Locker> getAvailableLocker() {
        return lockers.stream()
                .filter(locker -> locker.freeSpace() > 0)
                .max(Comparator.comparing(Locker::freeSpace));
    }
}

