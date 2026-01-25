package com.arnex.app.repository;

import java.util.List;

import com.arnex.app.entities.Book;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class BookRepositoryImpl implements BookRepository {
    
    private EntityManager em;

    public BookRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void add(Book book) {
        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        try {
            em.getTransaction().begin();
            Book b = em.find(Book.class, book.getId());
            if (!b.equals(book)) {
                b = book;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Book book) {
        try {
            em.getTransaction().begin();
            em.remove(book);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Book getBookById(Integer id) {
        return em.find(Book.class, id);
    }

    @Override
    public Book getBookByName(String name) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.name = :name", Book.class);
        query.setParameter("name", name);

        return query.getSingleResult();
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.author.name = :name", Book.class);
        query.setParameter("name", author);

        return query.getResultList();
    }
}
