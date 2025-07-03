package com.example.biblio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "formul_emprunt")
public class FormulEmprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomFormule;
    private int nombreDeMois;

    public FormulEmprunt() {}

    public FormulEmprunt(Long id, String nomFormule, int nombreDeMois) {
        this.id = id;
        this.nomFormule = nomFormule;
        this.nombreDeMois = nombreDeMois;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNomFormule() { return nomFormule; }
    public void setNomFormule(String nomFormule) { this.nomFormule = nomFormule; }

    public int getNombreDeMois() { return nombreDeMois; }
    public void setNombreDeMois(int nombreDeMois) { this.nombreDeMois = nombreDeMois; }
}