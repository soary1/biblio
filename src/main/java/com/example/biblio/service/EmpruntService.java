package com.example.biblio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.biblio.repository.FormulEmpruntRepository;
import com.example.biblio.model.FormulEmprunt;

@Service
public class EmpruntService {
    @Autowired
    private FormulEmpruntRepository formulEmpruntRepository;

    /**
     * Retourne le nombre de mois d'une formule à partir d'un profil donné.
     * @param profil le nom du profil à comparer à nomFormule
     * @return le nombre de mois si trouvé, sinon 0
     */
    public int getNombreDeMoisPourProfil(String profil) {
        FormulEmprunt formule = formulEmpruntRepository.findByNomFormule(profil);
        if (formule != null) {
            return formule.getNombreDeMois();
        }
        return 0;
    }
}
