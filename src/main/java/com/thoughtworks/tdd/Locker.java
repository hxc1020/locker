package com.thoughtworks.tdd;

import java.util.HashMap;
import java.util.Map;

public class Locker {
    private final int capacity;
    private final Map<Ticket, Bag> store;

    public Locker(int capacity) {
        this.capacity = capacity;
        this.store = new HashMap<>(this.capacity);
    }
    public Ticket save(Bag bag) throws Exception {
        if (store.size() >= capacity) {
            throw new Exception("Locker is full");
        }
        return new Ticket();
    }
}