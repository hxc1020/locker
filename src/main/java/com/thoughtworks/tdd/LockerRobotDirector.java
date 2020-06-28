package com.thoughtworks.tdd;

public class LockerRobotDirector {
    private LockerRobotManager lockerRobotManager;

    public LockerRobotDirector(LockerRobotManager lockerRobotManager) {
        this.lockerRobotManager = lockerRobotManager;
    }

    public String getReport() {
        int managerLockersCapacity = lockerRobotManager.getLockers().stream().mapToInt(Locker::getCapacity).sum();
        int managerLockersAvailableCapacity = lockerRobotManager.getLockers().stream().mapToInt(Locker::freeSpace).sum();
        return "M  " + managerLockersAvailableCapacity + "  " + managerLockersCapacity + "\n" + "    L  " +
                lockerRobotManager.getLockers().get(0).freeSpace() + "  " + lockerRobotManager.getLockers().get(0).getCapacity() + "\n"
                + "    L  " + lockerRobotManager.getLockers().get(1).freeSpace() + "  " + lockerRobotManager.getLockers().get(1).getCapacity();
    }
}
