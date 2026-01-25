package com.arnex.app;

import java.util.List;

import com.arnex.app.entities.ArtClass;
import com.arnex.app.entities.Review;
import com.arnex.app.entities.Student;
import com.arnex.app.entities.Teacher;
import com.arnex.app.repository.ReviewRepositoryImpl;
import com.arnex.app.repository.StudentRepository;
import com.arnex.app.repository.StudentRepositoryImpl;
import com.arnex.app.repository.TeacherRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("artclass_persistence_unit");

        // create(emf);
        // update(emf);
        // attachAndDetach(emf);
        // remove(emf);
        // oneToOneRelationship(emf);
        // oneToManyRelationship(emf);
        // manyToManyRelationship(emf);
        // writeQueries(emf);
        useRepository(emf);
    }

    public static void create(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Student student = new Student();
            student.setName("“John”");
            em.persist(student);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void update(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Student foundStudent = em.find(Student.class, 4);
            foundStudent.setName("Peter");

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void attachAndDetach(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Student student = new Student();
            student.setName("Mary");
            em.merge(student);

            em.detach(student);
            student.setName("Arnex");

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void remove(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Student student = em.find(Student.class, 4);
            em.remove(student);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void oneToOneRelationship(EntityManagerFactory emf) {
       EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

             ArtClass c = new ArtClass();
            c.setName("Oil Painting");
            c.setDayOfWeek("Monday");

            Teacher t = new Teacher();
            t.setName("Brown");

            c.setTeacher(t);

            em.persist(c);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void oneToManyRelationship(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Teacher t = new Teacher();
            t.setName("Woods");

            Review r1 = new Review();
            r1.setComment("Excellent!");
            r1.setRating(5);
            r1.setTeacher(t);

            Review r2 = new Review();
            r2.setComment("Good!");
            r2.setRating(4);
            r2.setTeacher(t);

            t.setReviews(List.of(r1, r2));

            em.persist(t);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void manyToManyRelationship(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            ArtClass c1 = new ArtClass();
            c1.setName("Recycled Scrap Art");
            c1.setDayOfWeek("Tuesday");

            ArtClass c2 = new ArtClass();
            c2.setName("Drawing Hands");
            c2.setDayOfWeek("Wednesday");

            Teacher t1 = new Teacher();
            t1.setName("White");

            Teacher t2 = new Teacher();
            t2.setName("Wood");

            c1.setTeacher(t1);
            c2.setTeacher(t2);

            Student s1 = new Student();
            s1.setName("Anne");
            Student s2 = new Student();
            s2.setName("Martin");

            c1.setStudents(List.of(s1, s2));
            c2.setStudents(List.of(s1, s2));

            em.persist(c1);
            em.persist(c2);
            
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void writeQueries(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // 1. A list of all the students
            TypedQuery<Student> allStudentsQuery = em.createQuery("SELECT s FROM Student s", Student.class);
            List<Student> allStudents = allStudentsQuery.getResultList();
            for (Student student: allStudents) {
                System.out.println(student);
            }

            // 2. A list of students who are attending classes on Monday
            TypedQuery<Student> mondayStudentsQuery = em.createQuery("SELECT ac.students FROM ArtClass ac WHERE ac.dayOfWeek = :dayOfWeek", Student.class);
            mondayStudentsQuery.setParameter("dayOfWeek", "Monday");
            List<Student> mondayStudents = mondayStudentsQuery.getResultList();
            for (Student student: mondayStudents) {
                System.out.println(student);
            }

            // 3. Average rating for the teacher named “White”
            TypedQuery<Double> averageTeacherRatingQuery = em.createQuery("SELECT AVG(r.rating) FROM Review r WHERE r.teacher.name = :name", Double.class);
            averageTeacherRatingQuery.setParameter("name", "White");
            Double average = averageTeacherRatingQuery.getSingleResult();
            System.out.println("Average " + average);

            // 4. Average ratings for teachers
            TypedQuery<Object[]> averageRatingsForTeachersQuery = em.createQuery(
                "SELECT t.name, AVG(r.rating) FROM Review r INNER JOIN r.teacher t GROUP BY t.name",
                Object[].class
            );
            averageRatingsForTeachersQuery.getResultList().forEach(r -> System.out.println(r[0] + " " + r[1]));

            // 5. Average ratings for teachers arranged in the descending order of average rating
            TypedQuery<Object[]> averageRatingsForTeachersDescQuery = em.createQuery(
                "SELECT t.name, AVG(r.rating) FROM Review r INNER JOIN r.teacher t GROUP BY r.teacher.id ORDER BY AVG(r.rating) DESC",
                Object[].class
            );
            averageRatingsForTeachersDescQuery.getResultList().forEach(r -> System.out.println(r[0] + " " + r[1]));

            // 6. Average ratings for teachers having an average rating greater than 3, arranged in the descending order of average rating
            TypedQuery<Object[]> averageRatingsForTeachersGreaterThan3DescQuery = em.createQuery(
                "SELECT t.name, AVG(r.rating) FROM Review r INNER JOIN r.teacher t GROUP BY r.teacher.id HAVING AVG(r.rating) > 3 ORDER BY AVG(r.rating) DESC",
                Object[].class
            );
            averageRatingsForTeachersGreaterThan3DescQuery.getResultList().forEach(r -> System.out.println(r[0] + " " + r[1]));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void useRepository(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();
        try {
            StudentRepositoryImpl studentRepository = new StudentRepositoryImpl(em);
            ReviewRepositoryImpl reviewRepository = new ReviewRepositoryImpl(em);

            // 1. A list of all the students
            List<Student> allStudents = studentRepository.getAllStudents();
            for (Student student: allStudents) {
                System.out.println(student);
            }

            // 2. A list of students who are attending classes on Monday
            List<Student> mondayStudents = studentRepository.getStudentsForDay("Monday");
            for (Student student: mondayStudents) {
                System.out.println(student);
            }

            // 3. Average rating for the teacher named “White”
            Double average = reviewRepository.getAvgRatingForTeacher("White");
            System.out.println("Average " + average);

            // 4. Average ratings for teachers
            reviewRepository.getAvgRatingsByTeachers().forEach(r -> System.out.println(r));
        } finally {
            em.close();
        }
    }
}