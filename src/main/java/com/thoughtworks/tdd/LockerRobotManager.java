package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketIsInvalidException;

import java.util.List;

public class LockerRobotManager {
    private final List<Locker> lockers;
    private final List<Robot> robots;

    public LockerRobotManager(List<Locker> lockers, List<Robot> robots) {
        this.lockers = lockers;
        this.robots = robots;
    }

    public Ticket save(Bag bag) throws LockerIsFullException {
        Ticket ticket = null;
        for (Robot robot : robots) {
            if (robot.isFull()) {
                continue;
            }
            ticket = robot.save(bag);
        }
        for (Locker locker : lockers) {
            if (locker.isFull()) {
                continue;
            }
            ticket =  locker.save(bag);
        }
        if (ticket != null) {
            ticket.setType(TicketType.GIVEN_BY_MANAGER);
            return ticket;
        }
        throw new LockerIsFullException();
    }

    public Bag take(Ticket ticket) throws TicketIsInvalidException {
        if (ticket.getType().equals(TicketType.GIVEN_BY_MANAGER)) {
            for (Locker locker : lockers) {
                if (locker.hasBag(ticket)) {
                    return locker.take(ticket);
                }
            }

            for (Robot robot : robots) {
                if (robot.hasBag(ticket)) {
                    return robot.take(ticket);
                }
            }
        }
        throw  new TicketIsInvalidException();
    }
}
