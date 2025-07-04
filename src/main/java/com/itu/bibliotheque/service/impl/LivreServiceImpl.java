package com.itu.bibliotheque.service.impl;

import com.itu.bibliotheque.model.Livre;
import com.itu.bibliotheque.repository.LivreRepository;
import com.itu.bibliotheque.service.LivreService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivreServiceImpl implements LivreService {

    @Autowired
    private LivreRepository livreRepository;

    @Override
    public List<Livre> findAll() {
        return livreRepository.findAll();
    }

    @Override
    public Livre findById(Long id) {
        return livreRepository.findById(id).orElse(null);
    }

    @Override
    public Livre save(Livre livre) {
        return livreRepository.save(livre);
    }

    @Override
    public void deleteById(Long id) {
        livreRepository.deleteById(id);
    }
}
