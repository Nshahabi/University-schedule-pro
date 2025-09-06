package edu.name.schedule.cli;

import edu.name.schedule.model.*;
import edu.name.schedule.repo.*;
import edu.name.schedule.service.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        CourseRepository courseRepo = new InMemoryCourseRepository();
        seed(courseRepo);
        ScheduleService service = new ScheduleService(courseRepo, 18);

        Scanner in = new Scanner(System.in);
        System.out.print("Student ID: ");
        String sid = in.nextLine().trim();

        while (true) {
            System.out.println("\n1) List Courses  2) Enroll  3) Drop  4) View Schedule  5) GPA  0) Exit");
            System.out.print("> ");
            String choice = in.nextLine();
            try {
                switch (choice) {
                    case "1" -> courseRepo.findAll().forEach(System.out::println);
                    case "2" -> { System.out.print("Course ID: "); service.enroll(sid, in.nextLine().trim()); System.out.println("Enrolled."); }
                    case "3" -> { System.out.print("Course ID: "); service.drop(sid, in.nextLine().trim()); System.out.println("Dropped."); }
                    case "4" -> {
                        var sched = service.getSchedule(sid);
                        System.out.println("Schedule for " + sid + ": " + sched.getCourseIds());
                    }
                    case "5" -> {
                        Map<String,String> grades = new HashMap<>();
                        System.out.println("Enter grades like: ITSC-1212 A, blank to stop");
                        while (true) {
                            String line = in.nextLine().trim();
                            if(line.isEmpty()) break;
                            String[] p = line.split("\\s+");
                            if(p.length==2) grades.put(p[0], p[1].toUpperCase());
                        }
                        System.out.println("GPA: " + service.gpa(grades));
                    }
                    case "0" -> { System.out.println("Bye!"); return; }
                    default -> System.out.println("Unknown choice");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }

    private static void seed(CourseRepository repo) {
        repo.save(new Course("ITSC-1212","Intro to CS",3, List.of(
            new Meeting(DayOfWeek.MONDAY, LocalTime.of(9,30), LocalTime.of(10,45), "Woodward 130"),
            new Meeting(DayOfWeek.WEDNESDAY, LocalTime.of(9,30), LocalTime.of(10,45), "Woodward 130")
        )));
        repo.save(new Course("MATH-1241","Calculus I",4, List.of(
            new Meeting(DayOfWeek.TUESDAY, LocalTime.of(11,0), LocalTime.of(12,15), "Smith 204"),
            new Meeting(DayOfWeek.THURSDAY, LocalTime.of(11,0), LocalTime.of(12,15), "Smith 204")
        )));
        repo.save(new Course("PHYS-2101","Physics I",4, List.of(
            new Meeting(DayOfWeek.MONDAY, LocalTime.of(10,45), LocalTime.of(12,0), "EPIC 220"),
            new Meeting(DayOfWeek.WEDNESDAY, LocalTime.of(10,45), LocalTime.of(12,0), "EPIC 220")
        )));
    }
}
