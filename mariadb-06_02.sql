-- Drop database if any
DROP DATABASE IF EXISTS library;
-- Create a database
CREATE DATABASE library;
-- Use the database
USE library;
-- Crate tables in the database
CREATE TABLE IF NOT EXISTS book(
  book_id INT AUTO_INCREMENT PRIMARY KEY,
  book_name VARCHAR(255),
  isbn VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS author(
  author_id INT AUTO_INCREMENT PRIMARY KEY,
  author_name VARCHAR(255)
);
INSERT INTO author (author_name)
VALUES ('John');
ALTER TABLE IF EXISTS book
ADD COLUMN author_id INT;
ALTER TABLE book
ADD CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES author(author_id);
CREATE TABLE IF NOT EXISTS review(
  review_id INT AUTO_INCREMENT PRIMARY KEY,
  comment VARCHAR(255)
);
ALTER TABLE IF EXISTS review
ADD COLUMN book_id INT;
ALTER TABLE review
ADD CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES book(book_id);