package org.example.controller;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.example.entity.CD;
import org.example.entity.Emprunt;
import org.example.service.EmpruntService;

import java.util.List;

@Named
@RequestScoped
public class CDBean {

    @Inject
    private EmpruntService empruntService;

    private Long userId; // ID dyal user li mconnecté
    private Long cdId;   // ID dyal CD sélectionné

    public List<CD> getCdsDisponibles() {
        return empruntService.listerCDsDisponibles();
    }

    public void emprunterCD() {
        empruntService.emprunterCD(userId, cdId);
    }

    public List<Emprunt> getCdsEmpruntes() {
        return empruntService.listerCDsEmpruntes(userId);
    }

    public void retournerCD(Long empruntId) {
        empruntService.retournerCD(empruntId);
    }


    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public Long getCdId() { return cdId; }
    public void setCdId(Long cdId) { this.cdId = cdId; }
}

