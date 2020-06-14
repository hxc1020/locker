package com.thoughtworks.tdd;

import java.util.List;

public class Robot {
    private final List<Locker> lockers;

    public Robot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket save(Bag bag) throws Exception {
        return lockers.get(0).save(bag);
    }
}
