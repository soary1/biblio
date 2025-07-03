package com.example.biblio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;

import com.example.biblio.model.Abonnement;
import com.example.biblio.model.Emprunt;
import com.example.biblio.model.FormulEmprunt;
import com.example.biblio.model.*;
import com.example.biblio.repository.*;


import org.springframework.ui.Model;

@Controller
public class EmpruntController {

    @Autowired
    private EmpruntRepository empruntRepository;
    
    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private Sign_inRepository sign_inRepository;

    @Autowired
    private FormulEmpruntRepository formulEmpruntRepository;

    @GetMapping("/ajout-emprunt")
    public String afficherFormulaireEmprunt(
            @RequestParam(value = "emprunteurId", required = false) Long emprunteurId,
            Model model) {
        model.addAttribute("emprunt", new Emprunt());
        model.addAttribute("livres", livreRepository.findAll());
        model.addAttribute("adherent", sign_inRepository.findAll());

        Integer nombreDeMois = null;
        if (emprunteurId != null) {
            // 1. Récupérer l'utilisateur
            Sign_in emprunteur = sign_inRepository.findById(emprunteurId).orElse(null);
            if (emprunteur != null) {
                // 2. Récupérer le profil
                String profil = emprunteur.getProfil();
                // 3. Chercher la formule correspondante
                FormulEmprunt formule = formulEmpruntRepository.findByNomFormule(profil);
                if (formule != null) {
                    nombreDeMois = formule.getNombreDeMois();
                }
            }
        }
        model.addAttribute("nombreDeMois", nombreDeMois);

        return "ajout-emprunt";
    }

@PostMapping("/ajout-emprunt")
public String insererEmprunt(@ModelAttribute Emprunt emprunt, Model model) {
    Long emprunteurId = emprunt.getIdNomEmprunteur();
    Long livreId = emprunt.getIdLivre();

    Sign_in emprunteur = sign_inRepository.findById(emprunteurId).orElse(null);
    Livre livre = livreRepository.findById(livreId).orElse(null);

    if (emprunteur == null || livre == null) {
        model.addAttribute("message", "Utilisateur ou livre introuvable.");
        return "ajout-emprunt";
    }

    // 🔒 Vérification de l'âge minimum
    if (emprunteur.getDateNaissance() != null && livre.getAgeMinimum() != null) {
        int age = java.time.Period.between(emprunteur.getDateNaissance(), java.time.LocalDate.now()).getYears();
        if (age < livre.getAgeMinimum()) {
            model.addAttribute("message", "Âge insuffisant : vous devez avoir au moins " + livre.getAgeMinimum() + " ans pour emprunter ce livre.");
            model.addAttribute("emprunt", new Emprunt());
            model.addAttribute("livres", livreRepository.findAll());
            model.addAttribute("adherent", sign_inRepository.findAll());
            return "ajout-emprunt";
        }
    }

    // Gestion de la date de fin selon le type de lecture
    if ("sur place".equals(emprunt.getTypeDeLecture())) {
        emprunt.setDateFinEmprunt(null);
    } else if ("a emporte".equals(emprunt.getTypeDeLecture())) {
        Integer nombreDeMois = null;
        if (emprunteur != null) {
            String profil = emprunteur.getProfil();
            FormulEmprunt formule = formulEmpruntRepository.findByNomFormule(profil);
            if (formule != null) {
                nombreDeMois = formule.getNombreDeMois();
            }
        }
        if (nombreDeMois != null && emprunt.getDateDebutEmprunt() != null) {
            emprunt.setDateFinEmprunt(emprunt.getDateDebutEmprunt().plusMonths(nombreDeMois));
        } else {
            emprunt.setDateFinEmprunt(null);
        }
    }

    empruntRepository.save(emprunt);
    return "redirect:/ajout-emprunt";
}

    @GetMapping("/rendre-emprunt-surplace")
    public String afficherFormulaireRendu(Model model) {
        java.util.List<Emprunt> emprunts = empruntRepository.findEmpruntsEnCours();
        java.util.Map<Long, String> nomsEmprunteurs = new java.util.HashMap<>();
        for (Emprunt e : emprunts) {
            Sign_in user = sign_inRepository.findById(e.getIdNomEmprunteur()).orElse(null);
            nomsEmprunteurs.put(e.getId(), user != null ? user.getUserName() : "Inconnu");
        }
        model.addAttribute("empruntsEnCours", emprunts);
        model.addAttribute("nomsEmprunteurs", nomsEmprunteurs);
        return "rendre-emprunt-surplace";
    }

    @PostMapping("/rendre-emprunt-surplace")
    public String rendreEmpruntSurPlace(@RequestParam Long empruntId, @RequestParam String dateFinEmprunt) {
        Emprunt emprunt = empruntRepository.findById(empruntId).orElse(null);
        if (emprunt != null) {
            emprunt.setDateFinEmprunt(java.time.LocalDateTime.parse(dateFinEmprunt));
            empruntRepository.save(emprunt);
        }
        return "redirect:/rendre-emprunt-surplace";
    }
}
