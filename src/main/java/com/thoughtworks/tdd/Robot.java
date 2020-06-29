package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketIsInvalidException;

import java.util.Optional;

public interface Robot extends CapacityInfo {

    Ticket save(Bag bag) throws LockerIsFullException;

    Bag take(Ticket ticket) throws TicketIsInvalidException;

    Optional<Locker> getAvailableLocker();

    Optional<Locker> getLockerWhichHasBag(Ticket ticket);
}
