package com.esprit.workspace_wellbeing.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esprit.workspace_wellbeing.entity.Offre;

@Repository
public interface OffreRepository extends CrudRepository<Offre, Long>  {


}
