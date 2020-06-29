package com.thoughtworks.tdd;

import com.thoughtworks.tdd.exception.LockerIsFullException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LockerRobotDirectorTest {

    @Test
    @DisplayName("should get correct formatted report" +
            "when director get report " +
            "given director manage 1 manager, manager manage locker1 and locker2, manager manage no robot," +
            "locker's available capacity and capacity is 0,3; 4,5 individually")
    void task1() throws LockerIsFullException {
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(new Locker(3), new Locker(5)), Collections.emptyList());
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(Collections.singletonList(lockerRobotManager));

        lockerRobotManager.save(new Bag());
        lockerRobotManager.save(new Bag());
        lockerRobotManager.save(new Bag());
        lockerRobotManager.save(new Bag());

        String report = lockerRobotDirector.getReport();

        assertEquals("M  4  8\n    L  0  3\n    L  4  5", report);
    }

    @Test
    @DisplayName("should get correct formatted report" +
            "when director get report " +
            "given director manage 1 manager, manager manage robot1 and robot2, robot1 manage locker1, robot2 manage locker2," +
            "manager manage no locker," +
            "locker's available capacity and capacity is 4,9; 2,5 individually")
    void task2() throws LockerIsFullException {
        PrimaryLockerRobot primaryLockerRobot1 = new PrimaryLockerRobot(Collections.singletonList(new Locker(9)));
        PrimaryLockerRobot primaryLockerRobot2 = new PrimaryLockerRobot(Collections.singletonList(new Locker(5)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.emptyList(),
                Arrays.asList(primaryLockerRobot1, primaryLockerRobot2));
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(Collections.singletonList(lockerRobotManager));

        primaryLockerRobot1.save(new Bag());
        primaryLockerRobot1.save(new Bag());
        primaryLockerRobot1.save(new Bag());
        primaryLockerRobot1.save(new Bag());
        primaryLockerRobot1.save(new Bag());

        primaryLockerRobot2.save(new Bag());
        primaryLockerRobot2.save(new Bag());
        primaryLockerRobot2.save(new Bag());


        String report = lockerRobotDirector.getReport();

        assertEquals("M  6  14\n    R  4  9\n        L  4  9\n    R  2  5\n        L  2  5", report);
    }

    @Test
    @DisplayName("should get correct formatted report" +
            "when director get report " +
            "given director manage 1 manager, manager manage locker1 and robot1, robot1 manage locker2" +
            "manager manage no locker," +
            "locker's available capacity and capacity is 0,2; 0,1 individually")
    void task3() throws LockerIsFullException {
        PrimaryLockerRobot primaryLockerRobot1 = new PrimaryLockerRobot(Collections.singletonList(new Locker(1)));
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Collections.singletonList(new Locker(2)),
                Collections.singletonList(primaryLockerRobot1));
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(Collections.singletonList(lockerRobotManager));

        lockerRobotManager.save(new Bag());
        lockerRobotManager.save(new Bag());
        lockerRobotManager.save(new Bag());

        String report = lockerRobotDirector.getReport();

        assertEquals("M  0  3\n    L  0  2\n    R  0  1\n        L  0  1", report);
    }

    @Test
    @DisplayName("should get correct formatted report" +
            "when director get report " +
            "given director manage 2 manager, manager1 manage locker1 and robot1, robot1 manage locker2" +
            "manager2 manage locker3," +
            "locker's available capacity and capacity is 2,3; 1,2;0,2 individually")
    void task4() throws LockerIsFullException {
        PrimaryLockerRobot primaryLockerRobot1 = new PrimaryLockerRobot(Collections.singletonList(new Locker(2)));
        Locker locker1 = new Locker(3);
        LockerRobotManager lockerRobotManager1 = new LockerRobotManager(Collections.singletonList(locker1),
                Collections.singletonList(primaryLockerRobot1));
        LockerRobotManager lockerRobotManager2 = new LockerRobotManager(Collections.singletonList(new Locker(2)), Collections.emptyList());
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(Arrays.asList(lockerRobotManager1, lockerRobotManager2));

        locker1.save(new Bag());
        primaryLockerRobot1.save(new Bag());
        lockerRobotManager2.save(new Bag());
        lockerRobotManager2.save(new Bag());


        String report = lockerRobotDirector.getReport();

        assertEquals("M  3  5\n    L  2  3\n    R  1  2\n        L  1  2\nM  0  2\n    L  0  2", report);
    }

    @Test
    @DisplayName("should get correct formatted report" +
            "when director get report " +
            "given director manage 1 manager, manager manage locker1 and locker2,manager manage no robot" +
            "locker's available capacity and capacity is 1,3; 5,6 individually，" +
            "and there is a locker3 which is not managed by manager ")
    void task5() throws LockerIsFullException {
        Locker locker1 = new Locker(3);
        Locker locker2 = new Locker(6);
        LockerRobotManager lockerRobotManager = new LockerRobotManager(Arrays.asList(locker1, locker2),
                Collections.emptyList());
        LockerRobotDirector lockerRobotDirector = new LockerRobotDirector(Collections.singletonList(lockerRobotManager));
        Locker locker3 = new Locker(4);

        locker1.save(new Bag());
        locker1.save(new Bag());
        locker2.save(new Bag());

        String report = lockerRobotDirector.getReport();

        assertEquals("M  6  9\n    L  1  3\n    L  5  6", report);
    }
}
