package com.esprit.workspace_wellbeing.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.esprit.workspace_wellbeing.entity.Emotion;
@Repository
public interface EmotionRepository extends JpaRepository <Emotion, Integer> {

}
