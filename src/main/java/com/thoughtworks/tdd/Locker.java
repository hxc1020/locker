package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketIsInvalidException;

import java.util.HashMap;
import java.util.Map;

public class Locker {
    private final int capacity;
    private final Map<Ticket, Bag> store;

    public Locker(int capacity) {
        this.capacity = capacity;
        this.store = new HashMap<>(this.capacity);
    }

    public Ticket save(Bag bag) throws LockerIsFullException {
        if (isFull()) {
            throw new LockerIsFullException();
        }
        Ticket ticket = new Ticket();
        store.put(ticket, bag);
        return ticket;
    }

    public boolean isFull() {
        return store.size() >= capacity;
    }

    public Bag take(Ticket ticket) throws TicketIsInvalidException {
        if (!hasBag(ticket)) {
            throw new TicketIsInvalidException();
        }
        return store.remove(ticket);
    }

    public boolean hasBag(Ticket ticket) {
        return store.containsKey(ticket);
    }

    public int freeSpace() {
        return capacity - store.size();
    }

    public int getCapacity() {
        return capacity;
    }
}
