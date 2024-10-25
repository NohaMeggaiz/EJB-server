package org.example.service;

import jakarta.ejb.Remote;
import org.example.entity.CD;

import java.util.List;

@Remote
public interface BookServiceRemote {
    void addBook(CD cd);
    void updateBook(CD cd);
    void deleteBook(Long cdId);
    List<CD> getAllAvailableBooks();
}
