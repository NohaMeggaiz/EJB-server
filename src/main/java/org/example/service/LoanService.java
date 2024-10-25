package org.example.service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.example.entity.CD;
import org.example.entity.Emprunt;
import org.example.entity.User;

import java.time.LocalDate;
import java.util.List;

@Stateless
public class LoanService implements LoanServiceRemote {

    @PersistenceContext(unitName = "CDLibraryPU")
    private EntityManager entityManager;

    @Override
    public List<Emprunt> getAllLoans() {
        return entityManager.createQuery("SELECT e FROM Emprunt e", Emprunt.class).getResultList();
    }

    @Override
    public void loanCD(Long userId, Long cdId) {
        User user = entityManager.find(User.class, userId);
        CD cd = entityManager.find(CD.class, cdId);

        if (user != null && cd != null && cd.isDisponible()) {
            Emprunt emprunt = new Emprunt();
            emprunt.setUser(user);
            emprunt.setCd(cd);
            emprunt.setDateEmprunt(LocalDate.now());

            cd.setDisponible(false);

            entityManager.persist(emprunt);
            entityManager.merge(cd);
        }
    }

    @Override
    public void returnCD(Long empruntId) {
        Emprunt emprunt = entityManager.find(Emprunt.class, empruntId);
        if (emprunt != null && emprunt.getDateRetour() == null) {
            emprunt.setDateRetour(LocalDate.now());
            emprunt.getCd().setDisponible(true);

            entityManager.merge(emprunt);
            entityManager.merge(emprunt.getCd());
        }
    }
}

