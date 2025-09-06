package edu.name.schedule.repo;

import edu.name.schedule.model.Course;
import java.util.*;

public interface CourseRepository {
    Optional<Course> find(String id);
    void save(Course c);
    Collection<Course> findAll();
}
