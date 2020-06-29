package com.thoughtworks.tdd;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CapacityReport {
    private static final String FOUR_SPACES = "    ";
    private static final String TWO_SPACES = "  ";
    private final int availableCapacity;
    private final int capacity;
    private final ReportTag tag;
    private final List<CapacityReport> reports = new ArrayList<>();

    public CapacityReport(int availableCapacity, int capacity, ReportTag tag) {
        this.availableCapacity = availableCapacity;
        this.capacity = capacity;
        this.tag = tag;
    }

    public CapacityReport(List<CapacityReport> reports, ReportTag tag) {
        this.tag = tag;
        this.addAll(reports);
        this.availableCapacity = this.reports.stream().mapToInt(CapacityReport::getAvailableCapacity).sum();
        this.capacity = this.reports.stream().mapToInt(CapacityReport::getCapacity).sum();
    }

    public void addAll(List<CapacityReport> reports) {
        this.reports.addAll(reports);
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public String toString() {
        return tag.name() + TWO_SPACES + availableCapacity + TWO_SPACES + capacity
                + reports.stream()
                .map(Objects::toString)
                .map(str -> "\n" + FOUR_SPACES + str.replaceAll("\n", "\n" + FOUR_SPACES))
                .collect(Collectors.joining());
    }

    public enum ReportTag {
        M,
        R,
        L
    }
}