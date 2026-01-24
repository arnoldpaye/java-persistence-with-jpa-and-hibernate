USE artclass;
INSERT INTO student(student_name)
values ("Jane"),
  ("Allen"),
  ("John"),
  ("David"),
  ("Doe"),
  ("Austin");
INSERT INTO teacher(teacher_name)
values("White"),
  ("Wood"),
  ("Brown"),
  ("Waters");
INSERT INTO class(class_name, day_of_week, teacher_id)
values("Oil Painting", "Monday", 1),
  ("Recycled Scrap Art", "Tuesday", 2),
  ("Drawing Hands", "Wednesday", 3),
  ("Watercolor", "Thursday", 4);
INSERT INTO student_class(student_id, class_id)
values(1, 1),
  (1, 2),
  (1, 3),
  (1, 4),
  (2, 1),
  (2, 2),
  (2, 3),
  (2, 4),
  (3, 1),
  (3, 2),
  (3, 3),
  (4, 1),
  (4, 2),
  (4, 3);
INSERT INTO review(comment, rating, teacher_id)
values("Very Good", 4, 1),
  ("Excellent!", 5, 1),
  ("Good", 3, 1),
  ("Excellent!", 5, 2),
  ("Good", 3, 2),
  ("Very Good", 4, 3),
  ("Not bad", 2, 3),
  ("Excellent!", 5, 4),
  ("Very Good", 4, 4);