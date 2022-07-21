package com.esprit.workspace_wellbeing.controller;

import org.springframework.http.ResponseEntity;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.workspace_wellbeing.entity.Event;
import com.esprit.workspace_wellbeing.entity.Invitation;
import com.esprit.workspace_wellbeing.repository.EventRepository;
import com.esprit.workspace_wellbeing.repository.InvitationRepository;

import com.esprit.workspace_wellbeing.service.InvitationService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/application") 
public class InvitationController {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private InvitationRepository invitationRepository;

    
    @Autowired
    private InvitationService invitationService;

   /* @Autowired
    private NotificationRepository notificationRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private NotificationService notificationService;*/
    
 
     
    @GetMapping("/invitations")
    public ResponseEntity<List<Invitation>> GetAllInvitation() {
        List<Invitation> listInvitation = null;
        listInvitation = invitationRepository.findAll();
        if (listInvitation.isEmpty())
            return ResponseEntity.noContent().build();

        return new ResponseEntity<>(listInvitation, HttpStatus.OK);
    }
 
     
    @GetMapping("/invitations/{invitation_Id}")
    public ResponseEntity<Optional<Invitation>> GetInvitationById(@PathVariable long invitation_Id) {
        Optional<Invitation> listInvitation;
        listInvitation = invitationService.getInvitationById(invitation_Id);

        if (!listInvitation.isPresent())
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(listInvitation, HttpStatus.OK);

    }
 
    
    @GetMapping("/invitationsbyevent/{event_id}")
    public ResponseEntity<List<Invitation>> GetInvitationByEventId(@PathVariable long event_id) {
        List<Invitation> listInvitation; 
        Event event = eventRepository.findById(event_id).get(); 
        listInvitation = invitationRepository.findInvitationsByEvent(event);  
        return new ResponseEntity<>(listInvitation, HttpStatus.OK);

    }
    
    @GetMapping("/invitations/{event_id}/{receiver_id}")
    public ResponseEntity<List<Invitation>> existInvitationToSender(@PathVariable long event_id,@PathVariable Long receiver_id) {
        List<Invitation> listInvitation; 
        listInvitation=invitationRepository.existInvitationToSender(event_id,receiver_id);
        return new ResponseEntity<>(listInvitation, HttpStatus.OK);
    }

    @PutMapping("/invitations/{invitation_Id}")
    public ResponseEntity<Invitation> pullActivity(@PathVariable long invitation_Id,@RequestBody Invitation invitation) {
        
        Optional<Invitation> activityData = invitationService.getInvitationById(invitation_Id);
        
         if(activityData.isPresent()){
            Invitation invitationLocal = activityData.get();
            invitationLocal.setFavorit(invitation.getFavorit());

                 return new ResponseEntity<>(invitationRepository.save(invitationLocal),HttpStatus.OK);
         }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

    }
      
   
    @PostMapping("/invitations/{receiver_username}/{sender_username}/{event_id}")
    public ResponseEntity<?> addInvitation(@RequestBody Invitation invitationRequest,
    		@PathVariable String receiver_username,
    		@PathVariable String sender_username,
    		@PathVariable long event_id) throws MessagingException, IOException {
    
    	/*Long receiver_id=userRepository.findByUsername(receiver_username).get().getId();

    	  List<Invitation> listInvitations=invitationService.existInvitationToSender(event_id, receiver_id);
    	  if(listInvitations.size()>0) {
    		   return new ResponseEntity<>(new ResponseMessage("Fail -> Invitation already Sent to "+ receiver_username),
                       HttpStatus.BAD_REQUEST);
    		   }
    	  else {*/
    	  Invitation invitationLocal = invitationService.addInvitation(invitationRequest,receiver_username,sender_username ,event_id);
          if (invitationLocal == null)
              return ResponseEntity.noContent().build();
          return new ResponseEntity<>(invitationLocal, HttpStatus.OK);
    	 // }
    }


    @DeleteMapping("/invitations/{invitation_Id}")
    public ResponseEntity<Invitation> deleteInvitation(@PathVariable long invitation_Id) {
        invitationRepository.deleteById(invitation_Id);
        return ResponseEntity.accepted().build();
    }

 
}
