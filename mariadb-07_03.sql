USE library;
CREATE TABLE IF NOT EXISTS book_type(
  type_code VARCHAR(255),
  type_subcode VARCHAR(255),
  type_name VARCHAR(255),
  PRIMARY KEY(type_code, type_subcode)
);
INSERT INTO book_type(type_code, type_subcode, type_name)
values ("C001", "SC001", "Fiction-Horror"),
  ("C001", "SC002", "Fiction-Mistry"),
  ("C001", "SC003", "Fiction-Thriller");