package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketIsInvalidException;

import java.util.List;
import java.util.Optional;

public interface Robot {

    Ticket save(Bag bag) throws LockerIsFullException;

    Bag take(Ticket ticket) throws TicketIsInvalidException;

    Optional<Locker> getAvailableLocker();

    List<Locker> getLockers();
}
