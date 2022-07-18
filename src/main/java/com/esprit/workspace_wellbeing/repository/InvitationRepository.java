package com.esprit.workspace_wellbeing.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.esprit.workspace_wellbeing.entity.Event;
import com.esprit.workspace_wellbeing.entity.Invitation;
import com.esprit.workspace_wellbeing.entity.User;

@RepositoryRestResource(path = "invitation", collectionResourceRel = "invitation")
public interface InvitationRepository extends JpaRepository<Invitation,Long>{

    public List<Invitation> findInvitationsByEvent(Event event);
  
}
