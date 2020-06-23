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
        for (Robot robot : robots) {
            if (robot.isFull()) {
                continue;
            }
            return robot.save(bag);
        }
        for (Locker locker : lockers) {
            if (locker.isFull()) {
                continue;
            }
            return locker.save(bag);
        }
        throw new LockerIsFullException();
    }

    public Bag take(Ticket ticket) throws TicketIsInvalidException {
        return robots.get(0).take(ticket);
    }
}
