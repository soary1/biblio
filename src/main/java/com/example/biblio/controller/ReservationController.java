package com.example.biblio.controller;

import com.example.biblio.model.Reservation;
import com.example.biblio.model.Sign_in;
import com.example.biblio.model.Livre;
import com.example.biblio.repository.ReservationRepository;
import com.example.biblio.repository.Sign_inRepository;
import com.example.biblio.repository.LivreRepository;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Controller
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private Sign_inRepository signInRepository;

    @Autowired
    private LivreRepository livreRepository;

    @PostMapping("/reserver-livre")
    public String reserverLivre(
            @RequestParam("idLivre") Long idLivre,
            @RequestParam("dateReservation") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateReservation,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        Integer idSignIn = (Integer) session.getAttribute("id");

        if (idSignIn == null) {
            redirectAttributes.addFlashAttribute("message", "Vous devez être connecté pour réserver.");
            return "redirect:/login";
        }

        Optional<Sign_in> signInOpt = signInRepository.findById(Long.valueOf(idSignIn));
        Optional<Livre> livreOpt = livreRepository.findById(idLivre);

        if (signInOpt.isPresent() && livreOpt.isPresent()) {
            Sign_in user = signInOpt.get();
            Livre livre = livreOpt.get();

            // Vérification de l'âge minimum
            if (user.getDateNaissance() != null && livre.getAgeMinimum() != null) {
                int age = Period.between(user.getDateNaissance(), LocalDate.now()).getYears();
                if (age < livre.getAgeMinimum()) {
                    redirectAttributes.addFlashAttribute("message", "Réservation refusée : vous devez avoir au moins " + livre.getAgeMinimum() + " ans.");
                    return "redirect:/livres";
                }
            }

            // Enregistrement de la réservation
            Reservation reservation = new Reservation();
            reservation.setSignIn(user);
            reservation.setLivre(livre);
            reservation.setDateReservation(dateReservation);
            reservation.setStatut("en attente");

            reservationRepository.save(reservation);
            redirectAttributes.addFlashAttribute("message", "Réservation enregistrée avec succès !");
        } else {
            redirectAttributes.addFlashAttribute("message", "Utilisateur ou livre introuvable.");
        }

        return "redirect:/livres";
    }

    @GetMapping("/reservations")
    public String afficherReservations(Model model, HttpSession session) {
        String profil = (String) session.getAttribute("profil");
        if (!"admin".equalsIgnoreCase(profil)) {
            return "redirect:/login";
        }
        model.addAttribute("reservations", reservationRepository.findAll());
        return "liste-reservations";
    }

    @PostMapping("/reservation/accepter")
    public String accepterReservation(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        reservationRepository.findById(id).ifPresent(res -> {
            res.setStatut("acceptée");
            reservationRepository.save(res);
        });
        redirectAttributes.addFlashAttribute("message", "Réservation acceptée.");
        return "redirect:/reservations";
    }

    @PostMapping("/reservation/refuser")
    public String refuserReservation(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
        reservationRepository.findById(id).ifPresent(res -> {
            res.setStatut("refusée");
            reservationRepository.save(res);
        });
        redirectAttributes.addFlashAttribute("message", "Réservation refusée.");
        return "redirect:/reservations";
    }
}
