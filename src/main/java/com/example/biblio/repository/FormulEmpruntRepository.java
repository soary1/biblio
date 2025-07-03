package com.example.biblio.repository;

import com.example.biblio.model.FormulEmprunt;

import org.springframework.data.jpa.repository.JpaRepository;


public interface FormulEmpruntRepository extends JpaRepository<FormulEmprunt, Long> {
    FormulEmprunt findByNomFormule(String nomFormule);
}