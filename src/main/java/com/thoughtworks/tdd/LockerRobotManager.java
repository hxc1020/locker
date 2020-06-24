package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketIsInvalidException;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class LockerRobotManager {
    private final List<Locker> managerLockers;
    private final List<Locker> lockers;
    private final List<Robot> robots;

    public LockerRobotManager(List<Locker> managerLockers, List<Robot> robots) {
        this.managerLockers = managerLockers;
        this.robots = robots;
        this.lockers = this.robots.stream()
                .map(Robot::getLockers)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        this.lockers.addAll(this.managerLockers);
    }

    public Ticket save(Bag bag) throws LockerIsFullException {
        Ticket ticket;
        for (Robot robot : robots) {
            Optional<Locker> locker = robot.getAvailableLocker();
            if (locker.isPresent()) {
                ticket = locker.get().save(bag);
                ticket.setType(TicketType.GIVEN_BY_MANAGER);
                return ticket;
            }
        }
        for (Locker locker : managerLockers) {
            if (!locker.isFull()) {
                ticket =  locker.save(bag);
                ticket.setType(TicketType.GIVEN_BY_MANAGER);
                return ticket;
            }
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
        }
        throw  new TicketIsInvalidException();
    }
}
