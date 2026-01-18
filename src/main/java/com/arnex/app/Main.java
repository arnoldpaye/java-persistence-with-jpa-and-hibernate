package com.arnex.app;

import com.arnex.app.entities.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("artclass_persistence_unit");

        // create(emf);
        // update(emf);
        // attachAndDetach(emf);
        remove(emf);
    }

    public static void create(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Student student = new Student();
            student.setName("“John”");
            em.persist(student);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void update(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Student foundStudent = em.find(Student.class, 4);
            foundStudent.setName("Peter");

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void attachAndDetach(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Student student = new Student();
            student.setName("Mary");
            em.merge(student);

            em.detach(student);
            student.setName("Arnex");

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void remove(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Student student = em.find(Student.class, 4);
            em.remove(student);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}