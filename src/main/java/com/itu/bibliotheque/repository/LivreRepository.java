package com.itu.bibliotheque.repository;

import com.itu.bibliotheque.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LivreRepository extends JpaRepository<Livre, Long> {
}
