package com.example.biblio.controller;

import com.example.biblio.model.Sign_in;
import com.example.biblio.model.Abonnement;
import com.example.biblio.repository.*;

import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class Sign_inController {

    @Autowired
    private Sign_inRepository signInRepository;

    @Autowired
    private AbonnementRepository abonnementRepository;

    @GetMapping("/")
    public String redirigerVersLogin() {
        return "redirect:/login";
    }


    @GetMapping("/login")
    public String afficherFormulaireLogin(Model model,
        @RequestParam(required = false) String error) {
        if (error != null) {
            model.addAttribute("errorMessage", "Identifiants incorrects");
        }
        return "login";
    }

    // @PostMapping("/login")
    // public String traiterFormulaireLogin(@RequestParam String userName,
    //                                    @RequestParam String motDePasse) {
    //     Sign_in login = signInRepository.findByUserNameAndMotDePasse(userName, motDePasse);
    //     if (login != null) {
    //         return "redirect:/livres";
    //     } else {
    //         return "redirect:/login?error=true";
    //     }
    // }

    @GetMapping("/abonnement-expire")
    public String abonnementExpire() {
        return "abonnement-expire";
    }

    @PostMapping("/login")
// @PostMapping("/login")
public String traiterFormulaireLogin(@RequestParam String userName,
                                    @RequestParam String motDePasse,
                                    Model model,
                                    HttpSession session) {
    Sign_in login = signInRepository.findByUserNameAndMotDePasse(userName, motDePasse);
    if (login != null) {
        // Stocke les informations en session
        session.setAttribute("profil", login.getProfil());
        session.setAttribute("id", login.getId()); // ✅ LIGNE À AJOUTER

        if ("admin".equalsIgnoreCase(login.getProfil())) {
            return "redirect:/livres";
        }

        Abonnement abn = abonnementRepository.findByNomPersonne(userName);
        if (abn == null || abn.getFinAbonnement() == null || abn.getFinAbonnement().isBefore(LocalDate.now())) {
            return "redirect:/abonnement-expire";
        }

        return "redirect:/livres";
    } else {
        return "redirect:/login?error=true";
    }
}


    @GetMapping("/inscription")
    public String showInscriptionForm(Model model) {
        model.addAttribute("sign_in", new Sign_in());
        return "inscription"; // fichier inscription.jsp
    }

    @PostMapping("/inscription")
    public String processInscription(@ModelAttribute("sign_in") Sign_in signIn) {
        signInRepository.save(signIn);
        return "redirect:/success";
    }
}
