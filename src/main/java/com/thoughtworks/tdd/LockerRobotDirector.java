package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;

public class LockerRobotDirector {
    private final List<LockerRobotManager> lockerRobotManager;

    public LockerRobotDirector(List<LockerRobotManager> lockerRobotManager) {
        this.lockerRobotManager = lockerRobotManager;
    }

    public String getReport() {
        List<String> reports = new ArrayList<>();
        for (LockerRobotManager robotManager : lockerRobotManager) {
            int managerLockersAvailableCapacity = 0;
            int managerLockersCapacity = 0;
            if (!robotManager.getLockers().isEmpty()) {
                managerLockersAvailableCapacity = robotManager.getLockers().stream().mapToInt(Locker::freeSpace).sum();
                managerLockersCapacity = robotManager.getLockers().stream().mapToInt(Locker::getCapacity).sum();
            }
            if (!robotManager.getRobots().isEmpty()) {
                managerLockersAvailableCapacity += robotManager.getRobots().stream().mapToInt(Robot::getAvailableCapacity).sum();
                managerLockersCapacity += robotManager.getRobots().stream().mapToInt(Robot::getCapacity).sum();
            }

            reports.add("M  " + managerLockersAvailableCapacity + "  " + managerLockersCapacity);

            if (!robotManager.getLockers().isEmpty()) {
                for (Locker locker : robotManager.getLockers()) {
                    reports.add("    L  " + locker.freeSpace() + "  " + locker.getCapacity());
                }
            }
            if (!robotManager.getRobots().isEmpty()) {
                for (Robot robot : robotManager.getRobots()) {
                    reports.add("    R  " + robot.getAvailableCapacity() + "  " + robot.getCapacity());
                    for (Locker locker : robot.getLockers()) {
                        reports.add("        L  " + locker.freeSpace() + "  " + locker.getCapacity());
                    }
                }
            }
        }

        return String.join("\n", reports);
    }
}
