package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketIsInvalidException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SmartLockerRobotTest {

    @Test
    void should_saved_to_locker1_and_get_ticket_when_save_given_smartLockerRobot_manage_locker1_and_locker2_and_locker1Capacity_gt_locker2Capacity() throws TicketIsInvalidException, LockerIsFullException {
        Locker locker1 = new Locker(5);
        Locker locker2 = new Locker(4);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag givenBag = new Bag();
        Ticket ticket = smartLockerRobot.save(givenBag);

        assertNotNull(ticket);
        Bag bag = locker1.take(ticket);
        assertEquals(givenBag, bag);
    }
     @Test
    void should_saved_to_locker2_and_get_ticket_when_save_given_smartLockerRobot_manage_locker1_and_locker2_and_locker1Capacity_lt_locker2Capacity() throws TicketIsInvalidException, LockerIsFullException {
        Locker locker1 = new Locker(4);
        Locker locker2 = new Locker(5);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag givenBag = new Bag();
        Ticket ticket = smartLockerRobot.save(givenBag);

        assertNotNull(ticket);
        Bag bag = locker2.take(ticket);
        assertEquals(givenBag, bag);
    }
}
