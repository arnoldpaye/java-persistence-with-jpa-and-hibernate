package com.arnex.app;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import com.arnex.app.dto.BooksAndAuthors;
import com.arnex.app.entities.Address;
import com.arnex.app.entities.Author;
import com.arnex.app.entities.Book;
import com.arnex.app.entities.BookType;
import com.arnex.app.entities.CardPayment;
import com.arnex.app.entities.CashPayment;
import com.arnex.app.entities.Category;
import com.arnex.app.entities.Fiction;
import com.arnex.app.entities.Field;
import com.arnex.app.entities.Group;
import com.arnex.app.entities.Item;
import com.arnex.app.entities.NonFiction;
import com.arnex.app.entities.Review;
import com.arnex.app.entities.Student;
import com.arnex.app.entities.Teacher;
import com.arnex.app.entities.User;
import com.arnex.app.entities.keys.ItemKey;
import com.arnex.app.repository.BookRepository;
import com.arnex.app.repository.BookRepositoryImpl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("library_persistence_unit");

        // createInstance(emf);
        // findAndUpdateInstance(emf);
        // detachAndReattachInstance(emf);
        // removeInstance(emf);
        // useGetReference(emf);
        // useRefresh(emf);
        // createEntityWithComposePK(emf);
        // oneToOneRelationship(emf);
        // oneToManyRelationship(emf);
        // manyToManyRelationship(emf);
        // singleTableStrategy(emf);
        // joinedTableStrategy(emf);
        // tablePerClassStrategy(emf);
        // compositionWithAssociation(emf);
        // compositionWithEmbeddable(emf);
        // writeJPQLQuery(emf);
        // joinsWithJPQL(emf);
        // namedQueries(emf);
        // aggregateFunctions(emf);
        // orderBy(emf);
        // groupBy(emf);
        // having(emf);
        // nativeQueries(emf);
        // criteriaQueries(emf);
        useRepository(emf);
    }

    private static void createInstance(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager(); // Represent the persistence context

        try {
            em.getTransaction().begin();
            Book book = new Book();
            book.setName("my book 3");
            book.setIsbn("123-456");

            em.persist(book);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void findAndUpdateInstance(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Book foundBook = em.find(Book.class, 1);
            foundBook.setName("updated book"); // persisted on db
            System.out.println(foundBook);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void detachAndReattachInstance(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Book book1 = new Book(); // no in the context
            book1.setId(1);
            book1.setName("my update newest book");
            book1.setIsbn("123-456");

            em.merge(book1); // force into the context
            em.detach(book1);
            book1.setName("my another newest book");

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void removeInstance(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Book book1 = em.find(Book.class, 1);
            em.remove(book1); // remove from the context

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void useGetReference(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Book book2 = em.getReference(Book.class, 2); // no select
            System.out.println(book2); // query is executed

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public static void useRefresh(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            Book book2 = em.find(Book.class, 2);
            System.out.println(book2);
            book2.setName("some other book");
            System.out.println("Before " + book2);
            em.refresh(book2);
            System.out.println("After " + book2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void createEntityWithComposePK(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

           /*  BookType bookType = new BookType();
            bookType.setCode("C001");
            bookType.setSubCode("SC001");
            bookType.setName("Fiction-Horror");

            em.persist(bookType); */

            ItemKey id = new ItemKey();
            id.setCode("ABC");
            id.setNumber(100);

            Item item = new Item();
            item.setId(id);
            item.setName("ABC-100");

            em.persist(item);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void oneToOneRelationship(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Book book = new Book();
            book.setName("another book");
            book.setIsbn("1010-111");

            Author author = new Author();
            author.setName("John");

            book.setAuthor(author);

            em.persist(book);
            em.persist(author);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void oneToManyRelationship(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Book book = new Book();
            book.setName("book123");
            book.setIsbn("123-456");

            Author author = em.find(Author.class, 1);
            book.setAuthor(author);

            Review review1 = new Review();
            review1.setComment("This book is good");
            review1.setBook(book);
            Review review2 = new Review();
            review2.setComment("This book is lovely");
            review2.setBook(book);

            // book.setReviews(List.of(review1, review2));

            em.persist(book);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void manyToManyRelationship(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            User user1 = new User();
            user1.setName("User1");
            User user2 = new User();
            user2.setName("User2");

            Group group1 = new Group();
            group1.setName("Group1");
            Group group2 = new Group();
            group2.setName("Group2");

            group1.setUsers(List.of(user1, user2));
            group2.setUsers(List.of(user1));

            em.persist(group1);
            em.persist(group2);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void mappedSuperclassStrategy(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Student s = new Student();
            s.setName("John");
            s.setStudentCode("S001");

            Teacher t = new Teacher();
            t.setName("David");
            t.setTeacherCode("T001");

            em.persist(s);
            em.persist(t);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void singleTableStrategy(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Student s = new Student();
            s.setName("John");
            s.setStudentCode("S001");

            Teacher t = new Teacher();
            t.setName("David");
            t.setTeacherCode("T001");

            em.persist(s);
            em.persist(t);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void joinedTableStrategy(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Fiction f = new Fiction();
            f.setCode("F001");
            f.setSetting("Forest");

            NonFiction nf = new NonFiction();
            nf.setCode("NF001");
            nf.setTopic("Science");

            em.persist(f);
            em.persist(nf);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void tablePerClassStrategy(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            CardPayment card = new CardPayment();
            card.setId(100);
            card.setAmount(1000);
            card.setCardNumber("1234 5678 5677 3456");

            CashPayment cash = new CashPayment();
            cash.setId(101);
            cash.setAmount(2000);
            cash.setCode("CA001");

            em.persist(card);
            em.persist(cash);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void compositionWithAssociation(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Field f1 = new Field();
            f1.setName("Music");
            Field f2 = new Field();
            f2.setName("Art");

            Category c1 = new Category();
            c1.setName("History");
            Category c2 = new Category();
            c2.setName("New Advancements");

            f1.setCategories(Set.of(c1, c2));
            f2.setCategories(Set.of(c1, c2));

            c1.setFields(Set.of(f1, f2));
            c2.setFields(Set.of(f1, f2));

            em.persist(f1);
            em.persist(f2);


            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void compositionWithEmbeddable(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Author author = new Author();
            author.setName("Williams");

            Address address = new Address();
            address.setStreet("1st street");
            address.setCity("London");
            address.setPostalCode("12345");

            author.setAddress(address);

            em.persist(author);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void writeJPQLQuery(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            TypedQuery<BookType> q = em.createQuery("SELECT bt FROM BookType bt WHERE bt.subCode = :subCode AND bt.name LIKE :name", BookType.class);
            q.setParameter("subCode", "SC001");
            q.setParameter("name", "Fiction%");
            
            List<BookType> bookTypes = q.getResultList();

            for(BookType bt: bookTypes) {
                System.out.println(bt);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void joinsWithJPQL(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            /* String s = """
                    SELECT NEW com.arnex.app.dto.BooksAndAuthors(book, author, address) FROM Book book INNER JOIN book.author author
                    """; */
            String s = """
                    SELECT NEW com.arnex.app.dto.BooksAndAuthors(book, author, address) FROM Book book LEFT JOIN book.author author
                    """;

            TypedQuery<BooksAndAuthors> query = em.createQuery(s, BooksAndAuthors.class);
            List<BooksAndAuthors> result = query.getResultList();

            for(BooksAndAuthors r: result) {
                System.out.println(r.book() + " " + r.author() + " " + r.adress());
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void namedQueries(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            TypedQuery<BookType> q = em.createNamedQuery("bookType.findAll", BookType.class);
            List<BookType> bookTypes = q.getResultList();

            for(BookType bt: bookTypes) {
                System.out.println(bt);
            }

            TypedQuery<BookType> q2 = em.createNamedQuery("bookType.findBySubcodeAndName", BookType.class);
            q2.setParameter("subCode", "SC002");
            q2.setParameter("name", "Fiction%");

            List<BookType> bookTypes2 = q2.getResultList();

            for(BookType bt: bookTypes2) {
                System.out.println(bt);
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void aggregateFunctions(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            // COUNT() function
            String stringCount = "SELECT COUNT(b) FROM Book b WHERE b.author.name = :name";
            Query queryCount = em.createQuery(stringCount);
            queryCount.setParameter("name", "Allen");
            Long numberOfBooks = (Long) queryCount.getSingleResult();
            System.out.println("Number of book of author " + numberOfBooks);

            // SUM() function
            String stringSum = "SELECT SUM(b.price) FROM Book b WHERE b.author.name = :name";
            TypedQuery<BigDecimal> querySum = em.createQuery(stringSum, BigDecimal.class);
            querySum.setParameter("name", "Jane");
            BigDecimal totalCost = querySum.getSingleResult();
            System.out.println("Total cost of books of author " + totalCost);

            // MIN() function
            String stringMin = "SELECT MIN(r.rating) FROM Review r WHERE r.book.name = :name";
            TypedQuery<Integer> queryMin = em.createQuery(stringMin, Integer.class);
            queryMin.setParameter("name", "Book1");
            int minimumRating = queryMin.getSingleResult();
            System.out.println("Minimum rating for book " + minimumRating);

            // MAX() function
            String stringMax = "SELECT MAX(r.rating) FROM Review r WHERE r.book.name = :name";
            TypedQuery<Integer> queryMax = em.createQuery(stringMax, Integer.class);
            queryMax.setParameter("name", "Book1");
            int maximumRating = queryMax.getSingleResult();
            System.out.println("Maximum rating for book " + maximumRating);

            // AVG() function
            String stringAvg = "SELECT AVG(r.rating) FROM Review r WHERE r.book.name = :name";
            TypedQuery<Double> queryAvg = em.createQuery(stringAvg, Double.class);
            queryAvg.setParameter("name", "Book1");
            Double averageRating = queryAvg.getSingleResult();
            System.out.println("Average rating for book " + averageRating);

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void orderBy(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            /* String s = """
                    SELECT NEW com.arnex.app.dto.BooksAndAuthors(book, author, address) FROM
                    Book book LEFT JOIN book.author author
                    ORDER BY author.name
                    """; */
            String s = """
                    SELECT NEW com.arnex.app.dto.BooksAndAuthors(book, author, address) FROM
                    Book book LEFT JOIN book.author author
                    ORDER BY author.name DESC
                    """;
            TypedQuery<BooksAndAuthors> query = em.createQuery(s, BooksAndAuthors.class);
            List<BooksAndAuthors> result = query.getResultList();

            for (BooksAndAuthors r: result) {
                System.out.println(r.author() + " " + r.book());
            }

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void groupBy(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            /* String s = """
                    SELECT r.book.name, AVG(r.rating) FROM Review r
                    GROUP BY r.book.name
                    """; */

            String s = """
                    SELECT r.book.author.name, AVG(r.rating) FROM Review r
                    GROUP BY r.book.author.name
                    """;
            
            TypedQuery<Object[]> query = em.createQuery(s, Object[].class);

            // query.getResultList().forEach(o -> System.out.println("Average rating by book " + o[0] + " " + o[1]));

            query.getResultList().forEach(o -> System.out.println("Average rating by author " + o[0] + " " + o[1]));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void having(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            String s = """
                    SELECT r.book.author.name, AVG(r.rating) FROM Review r
                    GROUP BY r.book.author.name
                    HAVING AVG(r.rating) > 3
                    """;

            TypedQuery<Object[]> query = em.createQuery(s, Object[].class);
            query.getResultList().forEach(o -> System.out.println("Average rating by author " + o[0] + " " + o[1]));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void nativeQueries(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            String s = "SELECT* FROM book";
            Query q = em.createNativeQuery(s, Book.class);
            q.getResultList().forEach(r -> System.out.println(r));

            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    private static void criteriaQueries(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            CriteriaBuilder builder = em.getCriteriaBuilder();
            
            /*CriteriaQuery<BookType> cq = builder.createQuery(BookType.class);

            Root<BookType> bookTypeRoot = cq.from(BookType.class);

            cq.select(bookTypeRoot);

            TypedQuery<BookType> query = em.createQuery(cq);
            query.getResultList().forEach(r -> System.out.println(r)); */

            /* CriteriaQuery<String> cq = builder.createQuery(String.class);

            Root<BookType> bookTypeRoot = cq.from(BookType.class);

            cq.select(bookTypeRoot.get("name"));

            TypedQuery<String> query = em.createQuery(cq);
            query.getResultList().forEach(r -> System.out.println(r)); */

            /* CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);

            Root<BookType> bookTypeRoot = cq.from(BookType.class);

            cq.multiselect(bookTypeRoot.get("name"), bookTypeRoot.get("code"));

            TypedQuery<Object[]> query = em.createQuery(cq);
            query.getResultList().forEach(r -> System.out.println(r[0] + " " + r[1])); */

            CriteriaQuery<Object[]> cq = builder.createQuery(Object[].class);

            Root<Book> bookRoot = cq.from(Book.class);

            cq.multiselect(bookRoot.get("id"), bookRoot.get("name"), bookRoot.get("price"));
            cq.where(builder.gt(bookRoot.get("price"), 1000));
            cq.orderBy(builder.desc(bookRoot.get("price")));

            TypedQuery<Object[]> query = em.createQuery(cq);
            query.getResultList().forEach(r -> System.out.println(r[0] + " " + r[1] + " " + r[2]));

        } finally {
            em.close();
        }
    }

    private static void useRepository(EntityManagerFactory emf) {
        EntityManager em = emf.createEntityManager();

        try {
            BookRepository repository = new BookRepositoryImpl(em);

            // add
            Book b = new Book();
            b.setName("New book from repository");
            b.setIsbn("1234-1234");
            b.setPrice(new BigDecimal(2500));
            Author a = em.find(Author.class, 1);
            b.setAuthor(a);

            repository.add(b);

            // remove
            repository.remove(b);

            // query
            Book result = repository.getBookById(1);
            System.out.println(result);

            result = repository.getBookByName("Book1");
            System.out.println(result);

            List<Book> results = repository.getBooksByAuthor("Jane");
            results.forEach(o -> System.out.println(o));

            // update
            result.setPrice(new BigDecimal(100));
            repository.update(result);
        } finally {
            em.close();
        }
    }
}