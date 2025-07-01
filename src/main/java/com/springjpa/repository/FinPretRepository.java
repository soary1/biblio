package com.springjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.springjpa.entity.FinPret;

@Repository
public interface FinPretRepository extends JpaRepository<FinPret, Integer> {
}
