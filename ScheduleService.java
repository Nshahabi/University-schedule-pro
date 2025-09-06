package edu.name.schedule.service;

import edu.name.schedule.model.*;
import edu.name.schedule.repo.*;
import java.util.*;

public class ScheduleService {
    private final CourseRepository courses;
    private final Map<String, Schedule> schedules = new HashMap<>();
    private final int maxCredits;

    public ScheduleService(CourseRepository courses, int maxCredits) {
        this.courses = courses;
        this.maxCredits = maxCredits;
    }

    public Schedule getSchedule(String studentId) {
        return schedules.computeIfAbsent(studentId, Schedule::new);
    }

    public void enroll(String studentId, String courseId) {
        Course target = courses.find(courseId).orElseThrow(() -> new IllegalArgumentException("Course not found: " + courseId));
        Schedule sched = getSchedule(studentId);

        int currentCredits = sched.getCourseIds().stream()
                .map(courses::find).filter(Optional::isPresent).mapToInt(c -> c.get().getCredits()).sum();
        if (currentCredits + target.getCredits() > maxCredits)
            throw new IllegalStateException("Exceeds max credits: " + maxCredits);

        for (String id : sched.getCourseIds()) {
            Course existing = courses.find(id).orElseThrow();
            for (Meeting a : existing.getMeetings()) {
                for (Meeting b : target.getMeetings()) {
                    if (a.overlaps(b)) throw new IllegalStateException("Time conflict with " + existing.getId());
                }
            }
        }
        sched.add(courseId);
    }

    public void drop(String studentId, String courseId) {
        getSchedule(studentId).remove(courseId);
    }

    public double gpa(Map<String, String> letterGrades) {
        Map<String, Double> scale = Map.ofEntries(
          Map.entry("A",4.0), Map.entry("A-",3.7), Map.entry("B+",3.3), Map.entry("B",3.0), Map.entry("B-",2.7),
          Map.entry("C+",2.3), Map.entry("C",2.0), Map.entry("C-",1.7), Map.entry("D",1.0), Map.entry("F",0.0)
        );
        double qp = 0; int cr = 0;
        for (var e : letterGrades.entrySet()) {
            double pts = scale.getOrDefault(e.getValue(), 0.0);
            int c = courses.find(e.getKey()).map(Course::getCredits).orElse(0);
            qp += pts * c; cr += c;
        }
        return cr==0 ? 0.0 : Math.round((qp/cr)*100.0)/100.0;
    }
}
