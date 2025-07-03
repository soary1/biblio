package com.example.biblio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.biblio.model.Abonnement;

public interface AbonnementRepository extends JpaRepository<Abonnement, Long> {
    Abonnement findByNomPersonne(String nomPersonne);
}

