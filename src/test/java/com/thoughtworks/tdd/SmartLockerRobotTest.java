package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketIsInvalidException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleBiFunction;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void should_saved_to_locker1_and_get_ticket_when_save_given_smartLockerRobot_manage_locker1_and_locker2_and_locker1Capacity_equals_locker2Capacity() throws TicketIsInvalidException, LockerIsFullException {
        Locker locker1 = new Locker(4);
        Locker locker2 = new Locker(4);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag givenBag = new Bag();
        Ticket ticket = smartLockerRobot.save(givenBag);

        assertNotNull(ticket);
        Bag bag = locker1.take(ticket);
        assertEquals(givenBag, bag);
    }

    @Test
    void should_throw_LockerIsFullException_when_save_given_smartLockerRobot_manage_locker1_and_locker2_and_both_full() throws LockerIsFullException {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        smartLockerRobot.save(new Bag());
        smartLockerRobot.save(new Bag());

        assertThrows(LockerIsFullException.class, () -> smartLockerRobot.save(new Bag()));
    }

    @Test
    void should_get_bag_when_take_given_smartLockerRobot_manage_locker1_and_locker2_and_valid_ticket() throws LockerIsFullException, TicketIsInvalidException {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        Bag givenBag = new Bag();
        Ticket ticket = smartLockerRobot.save(givenBag);

        Bag bag = smartLockerRobot.take(ticket);

        assertEquals(givenBag, bag);
    }

    @Test
    void should_throw_TicketIsInvalidException_when_take_given_smartLockerRobot_manage_locker1_and_locker2_and_ticket_is_invalid() throws LockerIsFullException {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        smartLockerRobot.save(new Bag());
        Ticket ticket = new Ticket();

        assertThrows(TicketIsInvalidException.class, () -> smartLockerRobot.take(ticket));
    }
}
