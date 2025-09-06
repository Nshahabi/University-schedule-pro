package edu.name.schedule.repo;

import edu.name.schedule.model.Student;
import java.util.*;

public class InMemoryStudentRepository implements StudentRepository {
    private final Map<String, Student> map = new LinkedHashMap<>();

    public Optional<Student> find(String id) { return Optional.ofNullable(map.get(id)); }
    public void save(Student s) { map.put(s.getId(), s); }
    public Collection<Student> findAll() { return map.values(); }
}
