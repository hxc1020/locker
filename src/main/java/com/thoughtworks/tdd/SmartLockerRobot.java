package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SmartLockerRobot {
    private final List<Locker> lockers;

    public SmartLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket save(Bag bag) throws LockerIsFullException {
        lockers.sort(Comparator.comparing(Locker::freeSpace));
        if (lockers.get(0).freeSpace() != lockers.get(1).freeSpace()) {
            Collections.reverse(lockers);
        }
        return lockers.get(0).save(bag);
    }

}

