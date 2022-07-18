package com.esprit.workspace_wellbeing.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esprit.workspace_wellbeing.entity.Etat;
import com.esprit.workspace_wellbeing.enums.EtatName;


@Repository
public interface EtatRepository extends JpaRepository<Etat, Long> {
    Optional<Etat> findByName(EtatName etatName);
}

