package com.arnex.app.repository;

import java.util.List;

import com.arnex.app.dto.TeacherAvgRating;
import com.arnex.app.entities.Review;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class ReviewRepositoryImpl implements ReviewRepository {

    private EntityManager em;

    public ReviewRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void add(Review review) {
        try {
            em.getTransaction().begin();
            em.persist(review);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Review review) {
        try {
            em.getTransaction().begin();
            Review r = em.find(Review.class, review.getId());
            if (!r.equals(review)) {
                r = review;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(Review review) {
        try {
            em.getTransaction().begin();
            em.remove(review);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Double getAvgRatingForTeacher(String teacher) {
        TypedQuery<Double> query = em.createQuery("SELECT AVG(r.rating) FROM Review r WHERE r.teacher.name = :name", Double.class);
        query.setParameter("name", teacher);

        return query.getSingleResult();
    }

    @Override
    public List<TeacherAvgRating> getAvgRatingsByTeachers() {
        TypedQuery<TeacherAvgRating> query = em.createQuery("SELECT t.name, AVG(r.rating) FROM Review r INNER JOIN r.teacher t GROUP BY r.teacher.id", TeacherAvgRating.class);
        return query.getResultList();
    }
    
}
