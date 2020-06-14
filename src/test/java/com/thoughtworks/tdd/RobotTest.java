package com.thoughtworks.tdd;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

//DONE GIVEN:机器人管理2个柜子, 都有剩余空间 WHEN: 存包 THEN: 包存到1号柜子，得到票
//TODO GIVEN:机器人管理2个柜子，1号柜子满，2号柜子有剩余空间 WHEN: 存包 THEN: 包存到2号柜子，得到票
//TODO GIVEN:机器人管理2个柜子，都无剩余 空间 WHEN: 存包 THEN: 存包失败
//TODO GIVEN:机器人管理2个柜子，有效票 WHEN: 取包 THEN: 得到存的包
//TODO GIVEN:机器人管理2个柜子，取过的票 WHEN: 取包 THEN: 取包失败
//TODO GIVEN:机器人管理2个柜子，无效票 WHEN: 取包 THEN: 取包失败
public class RobotTest {

    @Test
    void should_get_ticket_and_save_1st_locker_when_save_given_robot_and_two_locker_and_both_has_capacity() throws Exception {
        Locker locker = new Locker(5);
        Robot robot = new Robot(Arrays.asList(locker, new Locker(6)));
        Bag givenBag = new Bag();

        Ticket ticket = robot.save(givenBag);

        assertNotNull(ticket);
        Bag bag = locker.take(ticket);
        assertEquals(givenBag, bag);
    }
}
