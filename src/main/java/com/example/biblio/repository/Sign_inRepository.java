package com.example.biblio.repository;

import com.example.biblio.model.Sign_in;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Sign_inRepository extends JpaRepository<Sign_in, Long> {
    Sign_in findByUserNameAndMotDePasse(String userName, String motDePasse);
    Sign_in findByUserName(String userName);
    // Sign_in findByNomFormule(String profil);
}