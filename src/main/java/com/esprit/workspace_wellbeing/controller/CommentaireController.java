package com.esprit.workspace_wellbeing.controller;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esprit.workspace_wellbeing.entity.Commentaire;
import com.esprit.workspace_wellbeing.entity.Event;
import com.esprit.workspace_wellbeing.repository.CommentaireRepository;
import com.esprit.workspace_wellbeing.repository.EventRepository;
import com.esprit.workspace_wellbeing.security.jwt.response.ResponseMessage;
import com.esprit.workspace_wellbeing.service.CommentaireService;

import java.util.List;
import java.util.Optional;
import java.util.Date ;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/application")
public class CommentaireController {
    
    @Autowired
    private EventRepository eventRepository;


    @Autowired
    private CommentaireRepository commentaireRepository;


    @Autowired
    private CommentaireService commentaireService;

     
    @GetMapping("/commentaires")
    public ResponseEntity<List<Commentaire>> GetAllCommentaire() {
        List<Commentaire> listCommentaire = null;
        listCommentaire = commentaireRepository.findAll();
        if (listCommentaire.isEmpty())
            return ResponseEntity.noContent().build();

        return new ResponseEntity<>(listCommentaire, HttpStatus.OK);
    }
 
     
    @GetMapping("/commentaires/{commentaire_Id}")
    public ResponseEntity<Optional<Commentaire>> GetCommentaireById(@PathVariable long commentaire_Id) {
        Optional<Commentaire> commentaire;
        commentaire = commentaireService.getCommentaireById(commentaire_Id);

        if (!commentaire.isPresent())
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(commentaire, HttpStatus.OK);

    }
 
    
    @GetMapping("/commentairesbyevent/{event_id}")
    public ResponseEntity<List<Commentaire>> GetCommentaireByEventId(@PathVariable long event_id) {
        List<Commentaire> commentaireList; 
        Event event = eventRepository.findById(event_id).get(); 
        commentaireList = commentaireRepository.findCommentairesByEvent(event);  
        return new ResponseEntity<>(commentaireList, HttpStatus.OK);
    }
  
    @GetMapping("/avgNotebyEvent/{event_id}")
    public ResponseEntity<?> avgNoteByEvent(@PathVariable long event_id) {
    	
    return new ResponseEntity<>(new ResponseMessage("The average note attributed by comments of the event "+ 
    		eventRepository.findById(event_id).get().getTitle()
    		+" is "+commentaireService.avgNoteByEvent(event_id)), HttpStatus.OK);
    }

    @PutMapping("/commentaires/{commentaire_Id}")
    public ResponseEntity<Commentaire> pullCommentaire(@PathVariable long commentaire_Id,@RequestBody Commentaire commentaire) {
        
        Optional<Commentaire> commentaireData = commentaireService.getCommentaireById(commentaire_Id);
        
         if(commentaireData.isPresent()){
            Commentaire commentaireLocal = commentaireData.get();
            commentaireLocal.setCommentaire(commentaire.getCommentaire());
            commentaireLocal.setDate_commentaire(commentaire.getDate_commentaire());
            commentaireLocal.setNote(commentaire.getNote()); 
                return new ResponseEntity<>(commentaireRepository.save(commentaireLocal),HttpStatus.OK);
         }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

    }

 
      
   
    @PostMapping("/commentaires/{event_id}")
    public ResponseEntity<Commentaire> addCommentaire(@RequestBody Commentaire calendar, @PathVariable long event_id) {
        Commentaire commentaireLocal = commentaireService.addCommentaire(calendar, event_id);
        if (commentaireLocal == null)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(commentaireLocal, HttpStatus.OK);
    }


    @DeleteMapping("/commentaires/{commentaire_Id}")
    public ResponseEntity<Commentaire> deleteCommentaire(@PathVariable long commentaire_Id) {
        commentaireRepository.deleteById(commentaire_Id);
        return ResponseEntity.accepted().build();
    }


}
