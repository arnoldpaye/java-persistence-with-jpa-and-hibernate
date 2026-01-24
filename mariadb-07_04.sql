USE library;
ALTER TABLE IF EXISTS book
ADD COLUMN author_id INT;
ALTER TABLE IF EXISTS book
ADD COLUMN price DECIMAL;
ALTER TABLE book
ADD CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES author(author_id);
ALTER TABLE IF EXISTS author
ADD COLUMN street VARCHAR(255),
  ADD COLUMN city VARCHAR(255),
  ADD COLUMN postal_code VARCHAR(255);
CREATE TABLE IF NOT EXISTS review(
  review_id INT AUTO_INCREMENT PRIMARY KEY,
  comment VARCHAR(255)
);
ALTER TABLE IF EXISTS review
ADD COLUMN book_id INT;
ALTER TABLE IF EXISTS review
ADD COLUMN rating INT;
ALTER TABLE review
ADD CONSTRAINT fk_book_id FOREIGN KEY (book_id) REFERENCES book(book_id);
INSERT INTO author(author_name, street, city, postal_code)
values ("Jane", "street1", "London", "12345"),
  ("Allen", "street2", "New York", "45678"),
  ("John", "street3", "London", "56789"),
  ("David", "street4", "London", "76789"),
  ("Doe", "street4", "Paris", "643343"),
  ("Austin", "street5", "Paris", "343333");
INSERT INTO book(book_name, isbn, author_id, price)
values ("Book1", "111-1111", 1, 1000),
  ("Book2", "222-2222", 1, 1500),
  ("Book3", "333-3333", 2, 2000),
  ("Book4", "444-444", 3, 1000),
  ("Book5", "555-555", 2, 1100);
INSERT INTO review(comment, book_id, rating)
VALUES("Excellent!", 1, 5),
  ("Very good", 1, 4),
  ("Good", 2, 3),
  ("Not too bad", 3, 3),
  ("Not too bad", 4, 3),
  ("Not too bad", 5, 3);