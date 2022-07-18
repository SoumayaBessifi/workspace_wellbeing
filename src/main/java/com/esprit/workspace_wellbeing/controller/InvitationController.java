package com.esprit.workspace_wellbeing.controller;

 
import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.workspace_wellbeing.entity.Etat; 
import com.esprit.workspace_wellbeing.entity.Event;
import com.esprit.workspace_wellbeing.entity.Invitation;
import com.esprit.workspace_wellbeing.entity.Notification;
import com.esprit.workspace_wellbeing.repository.EtatRepository;
import com.esprit.workspace_wellbeing.repository.EventRepository;
import com.esprit.workspace_wellbeing.repository.InvitationRepository;
import com.esprit.workspace_wellbeing.repository.NotificationRepository;
import com.esprit.workspace_wellbeing.security.jwt.request.InvitationForm;
import com.esprit.workspace_wellbeing.service.InvitationService;
import com.esprit.workspace_wellbeing.service.NotificationService;
import com.esprit.workspace_wellbeing.enums.EtatName;

import java.util.List;
import java.util.Optional;
import java.util.Date ;

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

    @Autowired
    private NotificationRepository notificationRepository;
    
    @Autowired
    private NotificationService notificationService;
    
    
  /*  @Autowired
    private EtatRepository etatRepository;*/
     
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
    

    @PutMapping("/invitations/{invitation_Id}")
    public ResponseEntity<Invitation> pullActivity(@PathVariable long invitation_Id,@RequestBody Invitation invitation) {
        
        Optional<Invitation> activityData = invitationService.getInvitationById(invitation_Id);
        
         if(activityData.isPresent()){
            Invitation invitationLocal = activityData.get();
            invitationLocal.setValidation(invitation.getValidation());
            invitationLocal.setFavoris(invitation.getFavoris());
            invitationLocal.setParticipation(invitation.getParticipation());

                 return new ResponseEntity<>(invitationRepository.save(invitationLocal),HttpStatus.OK);
         }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

    }

 
      
   
    @PostMapping("/invitations/{receiver_id}/{sender_id}/{event_id}")
    public ResponseEntity<Invitation> addInvitation(@RequestBody Invitation invitationRequest, @PathVariable long receiver_id, @PathVariable long sender_id, @PathVariable long event_id) {
    /*Long notifId=(long) 1;
    Notification  notification= new Notification(notifId++, "New Invitation", invitationRequest);
    notificationService.addNotification(notification,notifId++);	*/
    
     /*   Set<String> strRoles = invitationRequest.getValidation();
        Set<Etat> roles = new HashSet<>();
  
        strRoles.forEach(valid -> {
            switch (valid) {
                case "attente":
                    Etat adminRole = etatRepository.findByName(EtatName.ETAT_ATTENTE).get();
                    roles.add(adminRole);
                    break;
                case "accepter":
                Etat companyRole = etatRepository.findByName(EtatName.ETAT_ACCEPTER).get();
                             
                    roles.add(companyRole);

                    break;

                default:
                Etat userRole = etatRepository.findByName(EtatName.ETAT_REFUSER).get();
                             
                    roles.add(userRole);
            }
        });


        Invitation invitation = new Invitation(
            invitationRequest.getFavoris(),
             invitationRequest.getParticipation()
              );
                
             invitation.setValidation(roles);
        Invitation invitationLocal = invitationService.addInvitation(invitation,receiver_id,sender_id ,event_id);
  

        if (invitationLocal == null)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(invitationLocal, HttpStatus.OK);*/
    	  Invitation invitationLocal = invitationService.addInvitation(invitationRequest,receiver_id,sender_id ,event_id);
          if (invitationLocal == null)
              return ResponseEntity.noContent().build();
          return new ResponseEntity<>(invitationLocal, HttpStatus.OK);

    }


    @DeleteMapping("/invitations/{invitation_Id}")
    public ResponseEntity<Invitation> deleteInvitation(@PathVariable long invitation_Id) {
        invitationRepository.deleteById(invitation_Id);
        return ResponseEntity.accepted().build();
    }

 
}