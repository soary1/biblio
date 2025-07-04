package com.itu.bibliotheque.controller;

import jakarta.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.itu.bibliotheque.model.Bibliothecaire;
import com.itu.bibliotheque.model.Adherent;

@Controller
public class HomeController {

    // Page d'accueil bibliothécaire
    @GetMapping("/bibliothecaire/home")
    public String bibliothecaireHome(HttpSession session, Model model) {
        Object userObj = session.getAttribute("userBibliothecaire");
        if (userObj != null) {
                        model.addAttribute("user", userObj);
            return "bibliothecaire/home";
        } else {
            // Si l'utilisateur n'est pas connecté, rediriger vers la page de login
            return "redirect:/bibliothecaire/login";
        }
        // return "redirect:/bibliothecaire/login";

    }


    // Page d'accueil adherent
    @GetMapping("/adherent/home")
    public String adherentHome(HttpSession session, Model model) {
        Adherent user = (Adherent) session.getAttribute("userAdherent");
        if (user == null) {
            return "redirect:/adherent/login";
        }
        model.addAttribute("user", user);
        return "adherent/home";
    }
}
