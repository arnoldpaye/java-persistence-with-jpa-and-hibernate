package com.arnex.app.repository;

import java.util.List;

import com.arnex.app.entities.Student;

public interface StudentRepository {
    public void add(Student student);

    public void update(Student student);

    public void remove(Student student);

    public Student getStudentById(Integer id);

    public List<Student> getAllStudents();

    public List<Student> getStudentsForDay(String day);
}
