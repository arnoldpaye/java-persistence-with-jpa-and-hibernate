package com.arnex.app.repository;

import java.util.List;

import com.arnex.app.entities.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class StudentRepositoryImpl implements StudentRepository {
    private EntityManager em;

    public StudentRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void add(Student student) {
        try {
            em.getTransaction().begin();
            em.persist(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student student) {
        try {
            em.getTransaction().begin();
            Student s = em.find(Student.class, student.getId());
            if (!s.equals(student)) {
                s = student;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Student student) {
        try {
            em.getTransaction().begin();
            em.remove(student);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(Integer id) {
        return em.find(Student.class, id);
    }

    @Override
    public List<Student> getAllStudents() {
        TypedQuery<Student> query = em.createQuery("SELECT s FROM Student s", Student.class);
        return query.getResultList();
    }

    @Override
    public List<Student> getStudentsForDay(String day) {
        TypedQuery<Student> query = em.createQuery("SELECT ac.students FROM ArtClass ac WHERE ac.dayOfWeek = :dayOfWeek", Student.class);
        query.setParameter("dayOfWeek", day);
        return query.getResultList();
    }
}
