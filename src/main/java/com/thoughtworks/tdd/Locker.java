package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketNotValidException;

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
            throw new LockerIsFullException("Locker is full");
        }
        Ticket ticket = new Ticket();
        store.put(ticket, bag);
        return ticket;
    }

    public Bag take(Ticket ticket) throws Exception {
        if (!store.containsKey(ticket)) {
            throw new TicketNotValidException("Illegal ticket");
        }
        return store.remove(ticket);
    }
}