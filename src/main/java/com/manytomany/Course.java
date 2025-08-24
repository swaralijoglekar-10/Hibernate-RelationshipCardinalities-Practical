package com.manytomany;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "courses")
@Table(name = "courses")
public class Course {
    @Id

    private int id;

    private String title;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students = new HashSet<>();

    public Course() {}

    public Course(String title) {
        this.title = title;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Set<Student> getStudents() { return students; }
    public void setStudents(Set<Student> students) { this.students = students; }
}