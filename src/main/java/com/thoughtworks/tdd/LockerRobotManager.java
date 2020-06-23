package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;

import java.util.List;

public class LockerRobotManager {
    private final List<Locker> lockers;

    public LockerRobotManager(List<Locker> lockers, List<Object> emptyList) {
        this.lockers = lockers;
    }

    public Ticket save(Bag bag) throws LockerIsFullException {
        for (Locker locker : lockers) {
            if (locker.isFull()) {
                continue;
            }
            return locker.save(bag);
        }
        throw new LockerIsFullException();
    }
}
