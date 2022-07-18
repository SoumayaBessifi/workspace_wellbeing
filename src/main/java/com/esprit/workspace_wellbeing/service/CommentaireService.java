package com.esprit.workspace_wellbeing.service;

 
import org.springframework.beans.factory.annotation.Autowired; 

 import com.esprit.workspace_wellbeing.entity.Commentaire;
import com.esprit.workspace_wellbeing.repository.CommentaireRepository;
import com.esprit.workspace_wellbeing.repository.EventRepository;

import java.util.Optional;


 import org.springframework.stereotype.Component;

@Component 
public class CommentaireService {
    
    @Autowired
    private CommentaireRepository commentaireRepository;
  

      @Autowired
    private EventRepository eventRepository;
  

    public Commentaire addCommentaire(Commentaire commentaire,Long event_id) {
        commentaire.setEvent(eventRepository.findById(event_id).get());
 
        return commentaireRepository.save(commentaire);
    }
    


    public Optional<Commentaire> getCommentaireById(Long id) {
        return commentaireRepository.findById(id); 
    }


}
