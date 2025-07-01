package com.springjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springjpa.entity.Pret;
import com.springjpa.entity.*;
import com.springjpa.entity.TypePret;

@Repository
public interface PretRepository extends JpaRepository<Pret, Integer> {
long countByAdherant_IdAdherantAndTypePret(Long idAdherant, TypePret typePret);

    long countByAdherantAndTypePret(Adherant adherant, TypePret typePret);

}
