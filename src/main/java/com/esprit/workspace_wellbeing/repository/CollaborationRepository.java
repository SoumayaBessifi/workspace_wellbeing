package com.esprit.workspace_wellbeing.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.esprit.workspace_wellbeing.entity.Collaboration;



@Repository
public interface CollaborationRepository extends CrudRepository<Collaboration, Long>  {

Optional<Collaboration>  findByCollaborationName(String collaborationName);

@Query("SELECT c FROM Collaboration c WHERE " +
        "c.collaborationName LIKE CONCAT('%',:query, '%')" )
List<Collaboration> searchCollaboration(String query);

}


