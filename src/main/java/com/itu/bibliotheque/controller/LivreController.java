package com.itu.bibliotheque.controller;

import com.itu.bibliotheque.model.Livre;
import com.itu.bibliotheque.service.LivreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class LivreController {

    @Autowired
    private LivreService livreService;

    @GetMapping("/livres")
    public String listLivres(Model model) {
        List<Livre> livres = livreService.findAll();
        model.addAttribute("livres", livres);
        return "livres";
    }
}
