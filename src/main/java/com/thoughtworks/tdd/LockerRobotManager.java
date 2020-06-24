package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketIsInvalidException;

import java.util.List;
import java.util.Optional;

public class LockerRobotManager {
    private final List<Locker> lockers;
    private final List<Robot> robots;

    public LockerRobotManager(List<Locker> lockers, List<Robot> robots) {
        this.lockers = lockers;
        this.robots = robots;
    }

    public Ticket save(Bag bag) throws LockerIsFullException {
        Locker availableLocker = robots.stream()
                .map(Robot::getAvailableLocker)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findAny()
                .orElse(getAvailableLockerByManager());

        if (availableLocker != null) {
            Ticket ticket = availableLocker.save(bag);
            ticket.setType(TicketType.GIVEN_BY_MANAGER);
            return ticket;
        }

        throw new LockerIsFullException();
    }

    private Locker getAvailableLockerByManager() {
        for (Locker locker : lockers) {
            if (!locker.isFull()) {
                return locker;
            }
        }
        return null;
    }

    public Bag take(Ticket ticket) throws TicketIsInvalidException {
        if (ticket.getType().equals(TicketType.GIVEN_BY_MANAGER)) {
            Locker locker = robots.stream()
                    .map(robot -> robot.getLockerWhichHasBag(ticket))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .findAny()
                    .orElse(getLockerByManager(ticket));

            if (locker != null) {
                return locker.take(ticket);
            }
        }
        throw new TicketIsInvalidException();
    }

    private Locker getLockerByManager(Ticket ticket) {
        for (Locker locker : lockers) {
            if (locker.hasBag(ticket)) {
                return locker;
            }
        }
        return null;
    }
}
