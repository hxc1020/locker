package com.thoughtworks.tdd;

import java.util.List;

public class Robot {
    private final List<Locker> lockers;

    public Robot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket save(Bag bag) throws Exception {
        if (lockers.get(0).isFull()) {
            return lockers.get(1).save(bag);
        }
        return lockers.get(0).save(bag);
    }
}
