package com.esprit.workspace_wellbeing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esprit.workspace_wellbeing.entity.Interaction;

@Repository
public interface InteractionRepository extends JpaRepository<Interaction, Long>{

}
