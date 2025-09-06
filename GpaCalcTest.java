package edu.name.schedule;

import edu.name.schedule.model.*;
import edu.name.schedule.repo.*;
import edu.name.schedule.service.*;
import org.junit.jupiter.api.Test;
import java.time.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

public class GpaCalcTest {
    @Test void gpa_basic() {
        var repo = new InMemoryCourseRepository();
        repo.save(new Course("A", "A", 3, List.of(new Meeting(DayOfWeek.MONDAY, LocalTime.NOON, LocalTime.MAX, ""))));
        repo.save(new Course("B", "B", 4, List.of(new Meeting(DayOfWeek.TUESDAY, LocalTime.NOON, LocalTime.MAX, ""))));
        var svc = new ScheduleService(repo, 18);
        double g = svc.gpa(Map.of("A","A", "B","B"));
        assertEquals((4.0*3 + 3.0*4)/7.0, g, 0.01);
    }
}
