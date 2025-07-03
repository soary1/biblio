package com.example.biblio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import java.io.Serializable;
import java.time.LocalDate;
import jakarta.persistence.Column;
@Entity
@Table(name = "sign_in")
public class Sign_in implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName;
    private String motDePasse;
    private String email;
    private int numero;
    private String profil;
    @Column(name = "date_naissance")
private LocalDate dateNaissance;

// getter & setter
public LocalDate getDateNaissance() {
    return dateNaissance;
}
public void setDateNaissance(LocalDate dateNaissance) {
    this.dateNaissance = dateNaissance;
}


    // Constructeur par défaut
    public Sign_in() {}

    // Constructeur sans id (pour création)
    public Sign_in(String userName, String motDePasse, String email, int numero, String profil) {
        this.userName = userName;
        this.motDePasse = motDePasse;
        this.email = email;
        this.numero = numero;
        this.profil = profil;
    }

    // Constructeur avec id (pour édition)
    public Sign_in(Integer id, String userName, String motDePasse, String email, int numero, String profil) {
        this.id = id;
        this.userName = userName;
        this.motDePasse = motDePasse;
        this.email = email;
        this.numero = numero;
        this.profil = profil;
    }

    // Getters et Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    @Override
    public String toString() {
        return "Sign_in{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", motDePasse='[PROTÉGÉ]'" +
                ", email='" + email + '\'' +
                ", numero=" + numero +
                ", profil='" + profil + '\'' +
                '}';
    }
}