package com.esprit.workspace_wellbeing.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.esprit.workspace_wellbeing.entity.Commentaire;
import com.esprit.workspace_wellbeing.entity.Event;

@RepositoryRestResource(path = "commentaire", collectionResourceRel = "commentaire")
public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {
 
    public List<Commentaire> findCommentairesByEvent(Event event);

 
}
