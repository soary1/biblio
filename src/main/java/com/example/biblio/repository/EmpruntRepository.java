package com.example.biblio.repository;

import com.example.biblio.model.Emprunt;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpruntRepository extends JpaRepository<Emprunt, Long> {
        // Emprunt findByNomFormule(String nomFormule);
    @org.springframework.data.jpa.repository.Query("SELECT e FROM Emprunt e WHERE e.dateFinEmprunt IS NULL")
    java.util.List<Emprunt> findEmpruntsEnCours();
}