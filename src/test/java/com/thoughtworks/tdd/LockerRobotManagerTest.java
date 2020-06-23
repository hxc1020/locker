package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketIsInvalidException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;

public class LockerRobotManagerTest {

    @Test
    void should_saved_to_locker1_and_return_ticket_when_save_bag_given_manager_manage_2_lockers_and_0_robot_and_both_lockers_have_capacity() throws TicketIsInvalidException, LockerIsFullException {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(3);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker1, locker2), emptyList());

        Bag givenBag = new Bag();
        Ticket ticket = lockerRobotManager.save(givenBag);

        assertNotNull(ticket);
        assertEquals(givenBag, locker1.take(ticket));
    }

    @Test
    void should_saved_to_locker2_and_return_ticket_when_save_bag_given_manager_manage_2_lockers_and_0_robot_and_locker1_has_no_capacity() throws TicketIsInvalidException, LockerIsFullException {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(3);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker1, locker2), emptyList());
        locker1.save(new Bag());

        Bag givenBag = new Bag();
        Ticket ticket = lockerRobotManager.save(givenBag);

        assertNotNull(ticket);
        assertEquals(givenBag, locker2.take(ticket));
    }

    @Test
    void should_throw_LockerIsFullException_when_save_bag_given_manager_manage_2_lockers_and_0_robot_and_both_locker_have_no_capacity() throws LockerIsFullException {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker1, locker2), emptyList());
        locker1.save(new Bag());
        locker2.save(new Bag());

        assertThrows(LockerIsFullException.class, () -> lockerRobotManager.save(new Bag()));
    }

    @Test
    void should_saved_to_robot1s_locker_and_return_ticket_when_save_bag_given_manager_manage_2_robot_and_0_locker_and_both_robots_locker_have_capacity() throws TicketIsInvalidException, LockerIsFullException {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(3);
        Locker locker3 = new Locker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker2, locker3));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(emptyList(), Arrays.asList(primaryLockerRobot, smartLockerRobot));

        Bag givenBag = new Bag();
        Ticket ticket = lockerRobotManager.save(givenBag);

        assertNotNull(ticket);
        assertEquals(givenBag, locker1.take(ticket));
    }

    @Test
    void should_saved_to_robot2s_locker_and_return_ticket_when_save_bag_given_manager_manage_2_robot_and_0_locker_and_robot1s_locker_has_no_capacity() throws TicketIsInvalidException, LockerIsFullException {
        Locker locker1 = new Locker(3);
        Locker locker2 = new Locker(1);
        Locker locker3 = new Locker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker2, locker3));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(emptyList(), Arrays.asList(smartLockerRobot, primaryLockerRobot));
        smartLockerRobot.save(new Bag());
        smartLockerRobot.save(new Bag());


        Bag givenBag = new Bag();
        Ticket ticket = lockerRobotManager.save(givenBag);

        assertNotNull(ticket);
        assertEquals(givenBag, locker1.take(ticket));
    }

    @Test
    void should_throw_lockerIsFullException_when_save_bag_given_manager_manage_2_robot_and_0_locker_and_both_robot_have_no_capacity() throws LockerIsFullException {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        Locker locker3 = new Locker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        SmartLockerRobot smartLockerRobot = new SmartLockerRobot(Arrays.asList(locker2, locker3));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(emptyList(), Arrays.asList(smartLockerRobot, primaryLockerRobot));
        smartLockerRobot.save(new Bag());
        smartLockerRobot.save(new Bag());
        primaryLockerRobot.save(new Bag());

        assertThrows(LockerIsFullException.class, () -> lockerRobotManager.save(new Bag()));
    }

    @Test
    void should_save_to_robots_locker_when_save_bag_given_manager_manage_1_robot_and_1_locker_and_both_have_capacity() throws LockerIsFullException, TicketIsInvalidException {
        Locker locker1 = new Locker(4);
        Locker locker2 = new Locker(5);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Collections.singletonList(locker2));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.singletonList(locker1), Collections.singletonList(primaryLockerRobot));

        Bag givenBag = new Bag();
        Ticket ticket = lockerRobotManager.save(givenBag);

        assertNotNull(ticket);
        assertEquals(givenBag, locker2.take(ticket));

    }
}
