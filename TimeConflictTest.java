package edu.name.schedule;

import edu.name.schedule.model.*;
import org.junit.jupiter.api.Test;
import java.time.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TimeConflictTest {
    @Test void overlap_detects_conflict() {
        var a = new Meeting(DayOfWeek.MONDAY, LocalTime.of(9,0), LocalTime.of(10,0), "A");
        var b = new Meeting(DayOfWeek.MONDAY, LocalTime.of(9,30), LocalTime.of(10,30), "B");
        assertTrue(a.overlaps(b));
    }
    @Test void overlap_ignores_different_days() {
        var a = new Meeting(DayOfWeek.MONDAY, LocalTime.of(9,0), LocalTime.of(10,0), "A");
        var b = new Meeting(DayOfWeek.TUESDAY, LocalTime.of(9,0), LocalTime.of(10,0), "B");
        assertFalse(a.overlaps(b));
    }
}
