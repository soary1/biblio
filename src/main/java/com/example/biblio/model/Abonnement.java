package com.example.biblio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "abonnement")
public class Abonnement {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomPersonne;
    private int nbMoisAbonnement;
    private LocalDate dateDebutAbonnement;
    private LocalDate finAbonnement;

    public Abonnement() {
    }

    public Abonnement(Long id, String nomPersonne, int nbMoisAbonnement, LocalDate dateDebutAbonnement) {
        this.id = id;
        this.nomPersonne = nomPersonne;
        this.nbMoisAbonnement = nbMoisAbonnement;
        this.dateDebutAbonnement = dateDebutAbonnement;
        this.finAbonnement = dateDebutAbonnement != null
            ? dateDebutAbonnement.plusMonths(nbMoisAbonnement)
            : null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomPersonne() {
        return nomPersonne;
    }

    public void setNomPersonne(String nomPersonne) {
        this.nomPersonne = nomPersonne;
    }

    public int getNbMoisAbonnement() {
        return nbMoisAbonnement;
    }

    public void setNbMoisAbonnement(int nbMoisAbonnement) {
        this.nbMoisAbonnement = nbMoisAbonnement;
        if (this.dateDebutAbonnement != null) {
            this.finAbonnement = this.dateDebutAbonnement.plusMonths(nbMoisAbonnement);
        }
    }

    public LocalDate getDateDebutAbonnement() {
        return dateDebutAbonnement;
    }

    public void setDateDebutAbonnement(LocalDate dateDebutAbonnement) {
        this.dateDebutAbonnement = dateDebutAbonnement;
        if (dateDebutAbonnement != null) {
            this.finAbonnement = dateDebutAbonnement.plusMonths(this.nbMoisAbonnement);
        }
    }

    public LocalDate getFinAbonnement() {
        return finAbonnement;
    }

    public void setFinAbonnement(LocalDate finAbonnement) {
        this.finAbonnement = finAbonnement;
    }
}