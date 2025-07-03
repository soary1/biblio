package com.example.biblio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.stereotype.Controller;
import com.example.biblio.model.Abonnement;
import com.example.biblio.repository.AbonnementRepository;

@Controller
public class AbonnementController {
    @Autowired
    private AbonnementRepository abonnementRepository;

    @GetMapping("/ajout-abonnement")
    public String afficherFormulaireAbonnement(Model model) {
        model.addAttribute("abonnement", new Abonnement());
        return "ajout-abonnement";
    }

    @PostMapping("/ajout-abonnement")
    public String ajouterAbonnement(@ModelAttribute Abonnement abonnement) {
        abonnement.setFinAbonnement(
            abonnement.getDateDebutAbonnement().plusMonths(abonnement.getNbMoisAbonnement())
        );
        abonnementRepository.save(abonnement);
        return "redirect:/ajout-abonnement"; // ou une autre page de confirmation
    }
}
