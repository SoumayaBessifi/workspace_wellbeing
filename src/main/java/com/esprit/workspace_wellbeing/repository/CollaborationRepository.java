package com.esprit.workspace_wellbeing.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esprit.workspace_wellbeing.entity.Collaboration;


@Repository
public interface CollaborationRepository extends CrudRepository<Collaboration, Long>  {

Optional<Collaboration>  findByCollaborationName(String collaborationName);

}


