package org.example.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.entity.CD;

import java.util.List;

@Stateless
public class BookService implements BookServiceRemote {

    @PersistenceContext(unitName = "CDLibraryPU")
    private EntityManager entityManager;

    @Override
    public void addBook(CD cd) {
        entityManager.persist(cd);
    }

    @Override
    public void updateBook(CD cd) {
        entityManager.merge(cd);
    }

    @Override
    public void deleteBook(Long cdId) {
        CD cd = entityManager.find(CD.class, cdId);
        if (cd != null) {
            entityManager.remove(cd);
        }
    }

    @Override
    public List<CD> getAllAvailableBooks() {
        return entityManager.createQuery("SELECT cd FROM CD cd WHERE cd.disponible = true", CD.class).getResultList();
    }
}
