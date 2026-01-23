package com.arnex.app.dto;

import com.arnex.app.entities.Address;
import com.arnex.app.entities.Author;
import com.arnex.app.entities.Book;

public record BooksAndAuthors (Book book, Author author, Address adress) {}
