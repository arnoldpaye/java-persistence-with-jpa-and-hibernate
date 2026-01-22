USE library;
CREATE TABLE IF NOT EXISTS field(
  field_id INT AUTO_INCREMENT PRIMARY KEY,
  field_name VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS category(
  category_id INT AUTO_INCREMENT PRIMARY KEY,
  category_name VARCHAR(255)
);
CREATE TABLE IF NOT EXISTS field_category(
  field_id INT,
  category_id INT,
  FOREIGN KEY (field_id) REFERENCES field(field_id),
  FOREIGN KEY (category_id) REFERENCES category(category_id)
);
ALTER TABLE IF EXISTS author
ADD COLUMN street VARCHAR(255),
  ADD COLUMN city VARCHAR(255),
  ADD COLUMN postal_code VARCHAR(255);