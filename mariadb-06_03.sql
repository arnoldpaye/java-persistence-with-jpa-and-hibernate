USE library;

CREATE TABLE IF NOT EXISTS user(
  user_id INT AUTO_INCREMENT PRIMARY KEY,
  user_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS grp(
  group_id INT AUTO_INCREMENT PRIMARY KEY,
  group_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS user_group(
  user_id INT,
  group_id INT,
  FOREIGN KEY (user_id) REFERENCES user(user_id),
  FOREIGN KEY (group_id) REFERENCES grp(group_id)
);