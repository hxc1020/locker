package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketIsInvalidException;

public interface Robot {

    Ticket save(Bag bag) throws LockerIsFullException;

    Bag take(Ticket ticket) throws TicketIsInvalidException;
}
