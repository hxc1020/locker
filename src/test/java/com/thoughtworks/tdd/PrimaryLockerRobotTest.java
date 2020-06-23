package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import com.thoughtworks.tdd.exception.TicketIsInvalidException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

//DONE GIVEN:机器人管理2个柜子, 都有剩余空间 WHEN: 存包 THEN: 包存到1号柜子，得到票
//DONE GIVEN:机器人管理2个柜子，1号柜子满，2号柜子有剩余空间 WHEN: 存包 THEN: 包存到2号柜子，得到票
//DONE GIVEN:机器人管理2个柜子，都无剩余 空间 WHEN: 存包 THEN: 存包失败
//DONE GIVEN:机器人管理2个柜子，有效票 WHEN: 取包 THEN: 得到存的包
//DONE GIVEN:机器人管理2个柜子，取过的票 WHEN: 取包 THEN: 取包失败
//DONE GIVEN:机器人管理2个柜子，无效票 WHEN: 取包 THEN: 取包失败
public class PrimaryLockerRobotTest {

    @Test
    void should_get_ticket_and_save_1st_locker_when_save_given_robot_and_two_locker_and_both_has_capacity() throws Exception {
        Locker locker = new Locker(5);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker, new Locker(6)));
        Bag givenBag = new Bag();

        Ticket ticket = primaryLockerRobot.save(givenBag);

        assertNotNull(ticket);
        Bag bag = locker.take(ticket);
        assertEquals(givenBag, bag);
    }

    @Test
    void should_get_ticket_and_save_2st_locker_when_save_given_robot_and_two_locker_and_2nd_has_capacity() throws Exception {
        Locker locker1 = new Locker(1);
        locker1.save(new Bag());
        Locker locker2 = new Locker(6);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        Bag givenBag = new Bag();

        Ticket ticket = primaryLockerRobot.save(givenBag);

        assertNotNull(ticket);
        Bag bag = locker2.take(ticket);
        assertEquals(givenBag, bag);
    }

    @Test
    void should_throw_LockerIsFullException_when_save_given_robot_and_two_locker_both_no_capacity() throws LockerIsFullException {
        Locker locker1 = new Locker(1);
        locker1.save(new Bag());
        Locker locker2 = new Locker(1);
        locker2.save(new Bag());
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        Bag givenBag = new Bag();

        assertThrows(LockerIsFullException.class, () -> primaryLockerRobot.save(givenBag));
    }


    @Test
    void should_get_bag_when_take_given_robot_and_two_locker_and_valid_ticket() throws LockerIsFullException, TicketIsInvalidException {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        Bag givenBag = new Bag();
        Ticket ticket = primaryLockerRobot.save(givenBag);

        Bag bag = primaryLockerRobot.take(ticket);

        assertEquals(givenBag, bag);
    }

    @Test
    void should_throw_TicketIsInvalidException_when_take_given_robot_and_two_locker_and_used_ticket() throws LockerIsFullException, TicketIsInvalidException {
        Locker locker1 = new Locker(1);
        Locker locker2 = new Locker(1);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        Bag givenBag = new Bag();
        Ticket ticket = primaryLockerRobot.save(givenBag);

        primaryLockerRobot.take(ticket);

        assertThrows(TicketIsInvalidException.class, () -> primaryLockerRobot.take(ticket));
    }

    @Test
    void should_throw_TicketIsInvalidException_when_take_given_robot_and_two_locker_and_invalid_ticket() throws LockerIsFullException {
        Locker locker1 = new Locker(2);
        Locker locker2 = new Locker(4);
        PrimaryLockerRobot primaryLockerRobot = new PrimaryLockerRobot(Arrays.asList(locker1, locker2));
        Bag givenBag = new Bag();
        primaryLockerRobot.save(givenBag);

        assertThrows(TicketIsInvalidException.class, () -> primaryLockerRobot.take(new Ticket()));
    }
}
