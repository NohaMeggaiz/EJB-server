package org.example.service;

import jakarta.ejb.Remote;
import org.example.entity.Emprunt;

import java.util.List;

@Remote
public interface LoanServiceRemote {
    List<Emprunt> getAllLoans();
    void loanCD(Long userId, Long cdId);
    void returnCD(Long empruntId);
}

