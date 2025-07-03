package com.example.biblio.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import com.example.biblio.model.Livre;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Long idReservation;

    @ManyToOne
    @JoinColumn(name = "id_sign_in", nullable = false)
    private Sign_in signIn;

    @ManyToOne
    @JoinColumn(name = "id_livre", nullable = false)
    private Livre livre;

    @Column(name = "date_reservation", nullable = false)
    private LocalDate dateReservation;

    @Column(name = "statut")
    private String statut = "en attente";

    // Getters and setters
    public Long getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(Long idReservation) {
        this.idReservation = idReservation;
    }

    public Sign_in getSignIn() {
        return signIn;
    }

    public void setSignIn(Sign_in signIn) {
        this.signIn = signIn;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    public LocalDate getDateReservation() {
        return dateReservation;
    }

    public void setDateReservation(LocalDate dateReservation) {
        this.dateReservation = dateReservation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
