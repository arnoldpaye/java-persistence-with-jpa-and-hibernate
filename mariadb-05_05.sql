USE library;
CREATE TABLE IF NOT EXISTS book_type(
  type_code VARCHAR(255),
  type_subcode VARCHAR(255),
  type_name VARCHAR(255),
  PRIMARY KEY(type_code, type_subcode)
);
CREATE TABLE IF NOT EXISTS item(
  item_code VARCHAR(255),
  item_number INT,
  item_name VARCHAR(255),
  PRIMARY KEY(item_code, item_number)
);