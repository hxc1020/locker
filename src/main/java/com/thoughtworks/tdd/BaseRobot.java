package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.TicketIsInvalidException;

import java.util.List;

public abstract class BaseRobot implements Robot {
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

    public List<Locker> getLockers() {
        return lockers;
    }
}
