package com.springjpa.service;

import org.springframework.stereotype.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.springjpa.entity.*;
import com.springjpa.repository.*;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Service
public class PretService {
    @Autowired
    private AdherantRepository adherantRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private TypePretRepository typePretRepository;

    @Autowired
    private ExemplaireRepository exemplaireRepository;

    @Autowired
    private PenaliteRepository penaliteRepository;

    @Autowired
    private QuotaTypePretRepository quotaTypePretRepository;

    @Autowired
    private PretRepository pretRepository;

    public Pret findById(Integer id){
        return pretRepository.findById(id).get();
    }

    public List<Pret> findAll(){
        return pretRepository.findAll();
    }

    public void save(Pret pret){
        pretRepository.save(pret);
    }
    @Transactional
public String creerPret(Integer idExemplaire, Integer idAdherant, Integer idAdmin, Integer idTypePret) {
    Exemplaire exemplaire = exemplaireRepository.findById(idExemplaire)
        .orElseThrow(() -> new RuntimeException("Exemplaire introuvable"));
    Adherant adherant = adherantRepository.findById(idAdherant)
        .orElseThrow(() -> new RuntimeException("Adhérant introuvable"));
    Admin admin = adminRepository.findById(idAdmin)
        .orElseThrow(() -> new RuntimeException("Admin introuvable"));
    TypePret typePret = typePretRepository.findById(idTypePret)
        .orElseThrow(() -> new RuntimeException("Type de prêt introuvable"));

    if (!exemplaire.isDispo()) {
        return "Exemplaire déjà prêté.";
    }

    // Vérifier si pénalisé
    List<Penalite> penalites = penaliteRepository.findByAdherant(adherant);

    if (!penalites.isEmpty()) {
        return "Adhérant pénalisé.";
    }

    // Vérifier le quota
    Profil profil = adherant.getProfil();
    QuotaTypePret quota = quotaTypePretRepository.findByProfilAndTypePret(profil, typePret)
        .orElseThrow(() -> new RuntimeException("Quota non défini pour ce profil"));
long nbPrets = pretRepository.countByAdherantAndTypePret(adherant, typePret);
    if (nbPrets >= quota.getQuota()) {
        return "Quota dépassé pour ce type de prêt.";
    }

    // Créer le prêt
    Pret pret = new Pret();
    pret.setAdmin(admin);
    pret.setAdherant(adherant);
    pret.setExemplaire(exemplaire);
    pret.setTypePret(typePret);
    pret.setDateDebut(LocalDateTime.now());
    pretRepository.save(pret);

    // Marquer l'exemplaire comme non disponible
    exemplaire.setDispo(false);
    exemplaireRepository.save(exemplaire);

    return "Prêt effectué avec succès.";
}

}
