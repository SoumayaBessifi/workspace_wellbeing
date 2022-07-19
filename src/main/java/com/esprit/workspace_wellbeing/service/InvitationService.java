package com.esprit.workspace_wellbeing.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

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
  
    @Autowired
    private JavaMailSender mailSender;
  

    public Invitation addInvitation(Invitation invitation,Long receiver_id,Long sender_id,Long event_id) {
        invitation.setReceiver_user(userRepository.findById(receiver_id).get());
        invitation.setSender_user(userRepository.findById(sender_id).get());
        invitation.setEvent(eventRepository.findById(event_id).get());
        
        String receiver_mail= userRepository.findById(receiver_id).get().getMail();
        String sender_mail= userRepository.findById(sender_id).get().getMail();
        
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom(sender_mail);
        message.setTo(receiver_mail);
        message.setSubject("Invitation to:"+invitation.getEvent().getTitle() );
        message.setText(invitation.getEvent().toString());
        
        mailSender.send(message);
        
        return invitationRepository.save(invitation);
    }
    

    public Optional<Invitation> getInvitationById(Long id) {
        return invitationRepository.findById(id); 
    }


}
