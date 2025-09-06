package edu.name.schedule.model;

import java.util.Objects;

public class Student {
    private final String id;
    private final String name;

    public Student(String id, String name) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException("id required");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("name required");
        this.id = id;
        this.name = name;
    }

    public String getId() { return id; }
    public String getName() { return name; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student s)) return false;
        return id.equals(s.id);
    }

    @Override public int hashCode() { return Objects.hash(id); }

    @Override public String toString() { return name + " (" + id + ")"; }
}
