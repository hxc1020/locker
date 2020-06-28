package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.TicketIsInvalidException;

import java.util.List;
import java.util.Optional;

abstract class BaseRobot implements Robot {
    protected List<Locker> lockers;

    @Override
    public Bag take(Ticket ticket) throws TicketIsInvalidException {
        if (ticket.getType().equals(TicketType.GIVEN_BY_ROBOT)) {
            for (Locker locker : lockers) {
                if (locker.hasBag(ticket)) {
                    return locker.take(ticket);
                }
            }
        }
        throw new TicketIsInvalidException();
    }

    @Override
    public Optional<Locker> getLockerWhichHasBag(Ticket ticket) {
        return lockers.stream().filter(locker -> locker.hasBag(ticket)).findAny();
    }

    @Override
    public int getAvailableCapacity() {
        return lockers.stream().mapToInt(Locker::freeSpace).sum();
    }

    @Override
    public int getCapacity() {
        return lockers.stream().mapToInt(Locker::getCapacity).sum();
    }

    @Override
    public List<Locker> getLockers() {
        return lockers;
    }
}
