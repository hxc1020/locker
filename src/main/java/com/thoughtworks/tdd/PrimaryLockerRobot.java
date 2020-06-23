package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;

import java.util.List;

public class PrimaryLockerRobot extends BaseRobot {

    public PrimaryLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket save(Bag bag) throws LockerIsFullException {
        for (Locker locker : lockers) {
            if (!locker.isFull()) {
                return locker.save(bag);
            }
        }
        throw new LockerIsFullException();
    }
}
