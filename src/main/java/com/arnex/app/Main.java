package com.arnex.app;

import com.arnex.app.entities.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library_persistence_unit");

        // createInstance(emf);
        // findAndUpdateInstance(emf);
        // detachAndReattachInstance(emf);
        removeInstance(emf);
    }

    private static void createInstance(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager(); // Represent the persistence context

        try {
            em.getTransaction().begin();
            Book book = new Book();
            book.setName("my book 3");
            book.setIsbn("123-456");

            em.persist(book);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void findAndUpdateInstance(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Book foundBook = em.find(Book.class, 1);
            foundBook.setName("updated book"); // persisted on db
            System.out.println(foundBook);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void detachAndReattachInstance(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Book book1 = new Book(); // no in the context
            book1.setId(1);
            book1.setName("my update newest book");
            book1.setIsbn("123-456");

            em.merge(book1); // force into the context
            em.detach(book1);
            book1.setName("my another newest book");

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void removeInstance(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Book book1 = em.find(Book.class, 1);
            em.remove(book1); // remove from the context

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}