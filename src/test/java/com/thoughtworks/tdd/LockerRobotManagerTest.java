package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketIsInvalidException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class LockerRobotManagerTest {
    @Test
    void should_saved_to_locker1_and_return_ticket_when_save_bag_given_manager_manage_2_lockers_and_0_robot() throws TicketIsInvalidException, LockerIsFullException {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(3);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker1, locker2), emptyList());

        Bag givenBag = new Bag();
        Ticket ticket = lockerRobotManager.save(givenBag);

        assertNotNull(ticket);
        assertEquals(givenBag, locker1.take(ticket));
    }
}
