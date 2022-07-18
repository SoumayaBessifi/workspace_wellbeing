package com.esprit.workspace_wellbeing.service;




import org.springframework.beans.factory.annotation.Autowired; 

import com.esprit.workspace_wellbeing.entity.Invitation;

import com.esprit.workspace_wellbeing.repository.InvitationRepository;
import com.esprit.workspace_wellbeing.repository.EventRepository;
import com.esprit.workspace_wellbeing.repository.UserRepository;

import java.util.Optional;


 import org.springframework.stereotype.Component;

@Component 
public class InvitationService {
    
    @Autowired
    private InvitationRepository invitationRepository;
  

    @Autowired
    private UserRepository userRepository;

    

    @Autowired
    private EventRepository eventRepository;
  
  

    public Invitation addInvitation(Invitation invitation,Long receiver_id,Long sender_id,Long event_id) {
        invitation.setReceiver_user(userRepository.findById(receiver_id).get());
        invitation.setSender_user(userRepository.findById(sender_id).get());
        invitation.setEvent(eventRepository.findById(event_id).get());

        return invitationRepository.save(invitation);
    }
    

    public Optional<Invitation> getInvitationById(Long id) {
        return invitationRepository.findById(id); 
    }


}
