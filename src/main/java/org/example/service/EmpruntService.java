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
public class EmpruntService {

    @PersistenceContext(unitName = "CDLibraryPU")
    private EntityManager entityManager;

    public List<CD> listerCDsDisponibles() {
        return entityManager.createQuery("SELECT cd FROM CD cd WHERE cd.disponible = true", CD.class).getResultList();
    }

    public void emprunterCD(Long userId, Long cdId) {
        CD cd = entityManager.find(CD.class, cdId);
        User user = entityManager.find(User.class, userId);

        if (cd != null && cd.isDisponible()) {
            Emprunt emprunt = new Emprunt();
            emprunt.setUser(user);
            emprunt.setCd(cd);
            emprunt.setDateEmprunt(LocalDate.now());

            cd.setDisponible(false);

            entityManager.persist(emprunt);
            entityManager.merge(cd);
        }
    }

    public List<Emprunt> listerCDsEmpruntes(Long userId) {
        return entityManager.createQuery("SELECT e FROM Emprunt e WHERE e.user.id = :userId", Emprunt.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    public void retournerCD(Long empruntId) {
        Emprunt emprunt = entityManager.find(Emprunt.class, empruntId);

        if (emprunt != null && emprunt.getDateRetour() == null) {
            emprunt.setDateRetour(LocalDate.now());
            emprunt.getCd().setDisponible(true);

            entityManager.merge(emprunt);
            entityManager.merge(emprunt.getCd());
        }
    }
}
