package com.esprit.workspace_wellbeing.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.esprit.workspace_wellbeing.entity.Commentaire;
import com.esprit.workspace_wellbeing.entity.Event;
import com.esprit.workspace_wellbeing.entity.Invitation;

@RepositoryRestResource(path = "commentaire", collectionResourceRel = "commentaire")
public interface CommentaireRepository extends JpaRepository<Commentaire,Long> {
 
    public List<Commentaire> findCommentairesByEvent(Event event);

    @Query(value="SELECT AVG(note) from commentaire where id_event=?1 ", nativeQuery = true)
    public float avgNoteByEvent(Long event_id );
 
}
