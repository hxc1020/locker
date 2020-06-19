package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketIsInvalidException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

    @Test
    void should_get_bag_when_take_given_smartLockerRobot_manage_locker1_and_locker2_and_primaryLockerRobot_manage_locker2_and_locker3_and_ticket_which_is_saved_by_locker2_from_primaryLockerRobot() throws LockerIsFullException, TicketIsInvalidException {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        Locker locker3 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker2, locker3));
        Bag givenBag = new Bag();
        Ticket ticketFromLocker2 = primaryLockerRobot.save(givenBag);

        Bag bag = smartLockerRobot.take(ticketFromLocker2);

        assertEquals(givenBag, bag);
    }

    @Test
    void should_throw_TicketIsInvalidException_when_take_given_smartLockerRobot_manage_locker1_and_locker2_and_primaryLockerRobot_manage_locker2_and_locker3_and_ticket_which_is_saved_by_locker3_from_primaryLockerRobot() throws LockerIsFullException {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        Locker locker3 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker2, locker3));
        primaryLockerRobot.save(new Bag());
        // locker2 is full , givenBag will saved to locker3
        Bag givenBag = new Bag();
        Ticket ticketFromLocker3 = primaryLockerRobot.save(givenBag);

        assertThrows(TicketIsInvalidException.class, () -> smartLockerRobot.take(ticketFromLocker3));
    }

    @Test
    void should_get_bag_when_take_by_primaryLockerRobot_given_smartLockerRobot_manage_locker1_and_locker2_and_primaryLockerRobot_manage_locker2_and_locker3_and_ticket_which_is_saved_by_locker2_from_smartLockerRobot() throws LockerIsFullException, TicketIsInvalidException {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(2);
        Locker locker3 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker2, locker3));
        Bag givenBag = new Bag();
        Ticket ticketFromLocker2 = smartLockerRobot.save(givenBag);

        Bag bag = primaryLockerRobot.take(ticketFromLocker2);

        assertEquals(givenBag, bag);
    }

    @Test
    void should_throw_TicketIsInvalidException_when_take_by_primaryLockerTicket_given_smartLockerRobot_manage_locker1_and_locker2_and_primaryLockerRobot_manage_locker2_and_locker3_and_ticket_which_is_saved_by_locker1_from_smartLockerRobot() throws LockerIsFullException {
        Locker locker1 = new Locker(4);
        Locker locker2 = new Locker(1);
        Locker locker3 = new Locker(1);
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker1, locker2));
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker2, locker3));
        Bag givenBag = new Bag();
        Ticket ticketFromLocker1 = smartLockerRobot.save(givenBag);

        assertThrows(TicketIsInvalidException.class, () -> primaryLockerRobot.take(ticketFromLocker1));
    }
}
