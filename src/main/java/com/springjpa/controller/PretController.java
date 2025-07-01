package com.springjpa.controller;

import com.springjpa.entity.Adherant;
import com.springjpa.entity.Exemplaire;
import com.springjpa.service.AdherantService;
import com.springjpa.service.ExemplaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.springjpa.entity.*;
import com.springjpa.service.*;
import com.springjpa.repository.*;
import com.springjpa.repository.ExemplaireRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;



@Controller
@RequestMapping("/pret")
public class PretController {

    @Autowired
    private PretService pretService;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private AdherantRepository adherantRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TypePretRepository typePretRepository;

    @GetMapping("/form")
    public String afficherFormulairePret(Model model) {
        model.addAttribute("exemplaires", exemplaireRepository.findByDispoTrue());
        model.addAttribute("adherants", adherantRepository.findAll());
        model.addAttribute("admins", adminRepository.findAll());
        model.addAttribute("typesPret", typePretRepository.findAll());
        return "creerPret"; // correspond à WEB-INF/views/creerPret.jsp
    }

    @PostMapping("/creer")
    public String creerPret(
            @RequestParam Integer idExemplaire,
            @RequestParam Integer idAdherant,
            @RequestParam Integer idAdmin,
            @RequestParam Integer idTypePret,
            Model model
    ) {
        String message = pretService.creerPret(idExemplaire, idAdherant, idAdmin, idTypePret);
        model.addAttribute("message", message);

        // Recharge les listes
        model.addAttribute("exemplaires", exemplaireRepository.findByDispoTrue());
        model.addAttribute("adherants", adherantRepository.findAll());
        model.addAttribute("admins", adminRepository.findAll());
        model.addAttribute("typesPret", typePretRepository.findAll());

        return "creerPret";
    }

    @GetMapping("/")
    public String index() {
        return "index"; // Redirection vers la page d'accueil
    }



}