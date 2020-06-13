package com.thoughtworks.tdd;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

//DONE GIVEN LOCKER有空间，包 WHEN 存包 THEN 存包成功，得到票
//TODO GIVEN LOCKER没有空间 WHEN 存包 THEN 存包失败
//TODO GIVEN 有效的票，WHEN 取包 THEN 取包成功
//TODO GIVEN 取过包的有票 WHEN 取包 THEN 取包失败
//TODO GIVEN 伪造票据 WHEN 取包 THEN 取包失败
public class LockerTest {

    @Test
    void should_get_ticket_given_not_full_locker_and_bag_when_save_bag() {
        Locker locker = new Locker();
        Bag bag = new Bag();

        Ticket ticket = locker.save(bag);

        assertNotNull(ticket);

    }
}