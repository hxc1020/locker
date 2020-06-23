package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;

import java.util.List;

public class LockerRobotManager {
    private final List<Locker> lockers;
    private final List<Robot> robots;

    public LockerRobotManager(List<Locker> lockers, List<Robot> robots) {
        this.lockers = lockers;
        this.robots = robots;
    }

    public Ticket save(Bag bag) throws LockerIsFullException {
        try {
            return robots.get(0).save(bag);
        } catch (LockerIsFullException | IndexOutOfBoundsException e){
        }
        for (Locker locker : lockers) {
            if (locker.isFull()) {
                continue;
            }
            return locker.save(bag);
        }
        throw new LockerIsFullException();
    }
}
