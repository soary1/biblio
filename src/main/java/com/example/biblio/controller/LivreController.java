package com.example.biblio.controller;

import com.example.biblio.model.Livre;
import com.example.biblio.repository.LivreRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class LivreController {

    @Autowired
    private LivreRepository livreRepository;


    @GetMapping("/livres")
    public String afficherLivres(Model model, HttpSession session) {
        List<Livre> livres = livreRepository.findAll();
        model.addAttribute("livres", livres);

        String profil = (String) session.getAttribute("profil");
        model.addAttribute("profil", profil);

        return "interface";
    }
}
