package edu.name.schedule.model;

import java.util.*;

public class Schedule {
    private final String studentId;
    private final Set<String> courseIds = new LinkedHashSet<>();

    public Schedule(String studentId) {
        this.studentId = Objects.requireNonNull(studentId);
    }

    public String getStudentId() { return studentId; }
    public Set<String> getCourseIds() { return Collections.unmodifiableSet(courseIds); }

    public boolean add(String courseId) { return courseIds.add(courseId); }
    public boolean remove(String courseId) { return courseIds.remove(courseId); }
    public int count() { return courseIds.size(); }
}
