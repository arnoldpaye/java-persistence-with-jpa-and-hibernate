package com.arnex.app.repository;

import com.arnex.app.entities.ArtClass;

import jakarta.persistence.EntityManager;

public class ArtClassRepositoryImpl implements ArtClassRepository {

    private EntityManager em;

    public ArtClassRepositoryImpl(EntityManager em) {
        this.em = em;
    }

    @Override
    public void add(ArtClass artClass) {
        try {
            em.getTransaction().begin();
            em.persist(artClass);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(ArtClass artClass) {
        try {
            em.getTransaction().begin();
            ArtClass ac = em.find(ArtClass.class, artClass.getId());
            if (!ac.equals(artClass)) {
                ac = artClass;
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void remove(ArtClass artClass) {
        try {
            em.getTransaction().begin();
            em.remove(artClass);
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ArtClass getClassById(Integer id) {
        return em.find(ArtClass.class, id);
    }
}
