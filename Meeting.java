package edu.name.schedule.model;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

public class Meeting {
    private final DayOfWeek day;
    private final LocalTime start;
    private final LocalTime end;
    private final String location;

    public Meeting(DayOfWeek day, LocalTime start, LocalTime end, String location) {
        if (start.compareTo(end) >= 0) throw new IllegalArgumentException("start < end required");
        this.day = Objects.requireNonNull(day);
        this.start = Objects.requireNonNull(start);
        this.end = Objects.requireNonNull(end);
        this.location = location == null ? "TBA" : location;
    }

    public DayOfWeek getDay() { return day; }
    public LocalTime getStart() { return start; }
    public LocalTime getEnd() { return end; }
    public String getLocation() { return location; }

    public boolean overlaps(Meeting other) {
        if (this.day != other.day) return false;
        return this.start.isBefore(other.end) && other.start.isBefore(this.end);
    }
}
