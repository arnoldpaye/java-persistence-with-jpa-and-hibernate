package com.arnex.app;

import com.arnex.app.entities.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library_persistence_unit");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Book book = new Book();
            book.setName("my book");
            book.setIsbn("123-456");

            em.persist(book);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}