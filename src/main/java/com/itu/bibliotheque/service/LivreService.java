package com.itu.bibliotheque.service;

import com.itu.bibliotheque.model.Livre;
import java.util.List;

public interface LivreService {
    List<Livre> findAll();
    Livre findById(Long id);
    Livre save(Livre livre);
    void deleteById(Long id);
}
