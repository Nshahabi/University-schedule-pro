package edu.name.schedule.repo;

import edu.name.schedule.model.Student;
import java.util.*;

public interface StudentRepository {
    Optional<Student> find(String id);
    void save(Student s);
    Collection<Student> findAll();
}
