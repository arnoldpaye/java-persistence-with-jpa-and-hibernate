package com.arnex.app.repository;

import java.util.List;

import com.arnex.app.entities.Book;

public interface BookRepository {
    public void add(Book book);

    public void update(Book book);

    public void remove(Book book);

    public Book getBookById(Integer id);

    public Book getBookByName(String name);

    public List<Book> getBooksByAuthor(String author);
}
