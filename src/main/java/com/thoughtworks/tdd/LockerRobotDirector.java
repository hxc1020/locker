package com.thoughtworks.tdd;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LockerRobotDirector {
    private final List<LockerRobotManager> lockerRobotManager;

    public LockerRobotDirector(List<LockerRobotManager> lockerRobotManager) {
        this.lockerRobotManager = lockerRobotManager;
    }

    public String getReport() {
        return lockerRobotManager.stream()
                .map(LockerRobotManager::getReport)
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));
    }
}
