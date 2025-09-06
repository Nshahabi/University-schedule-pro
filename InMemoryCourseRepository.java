package edu.name.schedule.repo;

import edu.name.schedule.model.Course;
import java.util.*;

public class InMemoryCourseRepository implements CourseRepository {
    private final Map<String, Course> map = new LinkedHashMap<>();

    public Optional<Course> find(String id) { return Optional.ofNullable(map.get(id)); }
    public void save(Course c) { map.put(c.getId(), c); }
    public Collection<Course> findAll() { return map.values(); }
}
