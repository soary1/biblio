package com.springjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.springjpa.entity.*;
import java.util.Optional;
public interface QuotaTypePretRepository extends JpaRepository<QuotaTypePret, Integer> {
    Optional<QuotaTypePret> findByProfilAndTypePret(Profil profil, TypePret typePret);
}

