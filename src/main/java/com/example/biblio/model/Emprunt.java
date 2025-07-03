package com.example.biblio.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalDate;
import jakarta.persistence.Column;

@Entity
@Table(name = "emprunt")
public class Emprunt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_livre")
    private Long idLivre;

    @Column(name = "id_nom_Emprunteur")
    private Long idNomEmprunteur;

    @Column(name = "date_debut_emprunt")
    private LocalDateTime dateDebutEmprunt;

    @Column(name = "date_fin_emprunt")
    private LocalDateTime dateFinEmprunt;
    
    @Column(name = "type_de_lecture")
    private String typeDeLecture;

    public Emprunt() {}

    public Emprunt(Long id, Long idNomEmprunteur, Long idLivre, LocalDateTime dateDebutEmprunt, LocalDateTime dateFinEmprunt, String typeDeLecture) {
        this.id = id;
        this.idNomEmprunteur = idNomEmprunteur;
        this.idLivre = idLivre;
        this.dateDebutEmprunt = dateDebutEmprunt;
        this.dateFinEmprunt = dateFinEmprunt;
        this.typeDeLecture = typeDeLecture;
    }

    // Getters et setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getIdLivre() { return idLivre; }
    public void setIdLivre(Long idLivre) { this.idLivre = idLivre; }

    public Long getIdNomEmprunteur() {
        return idNomEmprunteur;
    }
    public void setIdNomEmprunteur(Long idNomEmprunteur) {
        this.idNomEmprunteur = idNomEmprunteur;
    }

    public LocalDateTime getDateDebutEmprunt() { return dateDebutEmprunt; }
    public void setDateDebutEmprunt(LocalDateTime dateDebutEmprunt) { this.dateDebutEmprunt = dateDebutEmprunt; }

    public LocalDateTime getDateFinEmprunt() { return dateFinEmprunt; }
    public void setDateFinEmprunt(LocalDateTime dateFinEmprunt) { this.dateFinEmprunt = dateFinEmprunt; }
    

    public String getTypeDeLecture() {
        return typeDeLecture;
    }
    
    public void setTypeDeLecture(String typeDeLecture) {
        this.typeDeLecture = typeDeLecture;
    }
}