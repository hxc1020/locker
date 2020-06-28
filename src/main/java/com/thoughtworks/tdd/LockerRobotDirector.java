package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class LockerRobotDirector {
    private final LockerRobotManager lockerRobotManager;

    public LockerRobotDirector(LockerRobotManager lockerRobotManager) {
        this.lockerRobotManager = lockerRobotManager;
    }

    public String getReport() {
        List<String> reports = new ArrayList<>();
        int managerLockersAvailableCapacity = 0;
        int managerLockersCapacity = 0;
        if (!lockerRobotManager.getLockers().isEmpty()) {
            managerLockersAvailableCapacity = lockerRobotManager.getLockers().stream().mapToInt(Locker::freeSpace).sum();
            managerLockersCapacity = lockerRobotManager.getLockers().stream().mapToInt(Locker::getCapacity).sum();
        }
        if (!lockerRobotManager.getRobots().isEmpty()) {
            managerLockersAvailableCapacity += lockerRobotManager.getRobots().stream().mapToInt(Robot::getAvailableCapacity).sum();
            managerLockersCapacity += lockerRobotManager.getRobots().stream().mapToInt(Robot::getCapacity).sum();
        }

        reports.add("M  " + managerLockersAvailableCapacity + "  " + managerLockersCapacity);

        if (!lockerRobotManager.getLockers().isEmpty()) {
            for (Locker locker : lockerRobotManager.getLockers()) {
                reports.add("    L  " + locker.freeSpace() + "  " + locker.getCapacity());
            }
        }
        if (!lockerRobotManager.getRobots().isEmpty()) {
            for (Robot robot : lockerRobotManager.getRobots()) {
                reports.add("    R  " + robot.getAvailableCapacity() + "  " + robot.getCapacity());
                for (Locker locker : robot.getLockers()) {
                    reports.add("        L  " + locker.freeSpace() + "  " + locker.getCapacity());
                }
            }
        }
        return String.join("\n", reports);
    }
}
