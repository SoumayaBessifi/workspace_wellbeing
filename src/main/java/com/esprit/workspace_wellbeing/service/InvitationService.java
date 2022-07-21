package com.esprit.workspace_wellbeing.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import com.esprit.workspace_wellbeing.entity.Invitation;

import com.esprit.workspace_wellbeing.repository.InvitationRepository;
import com.esprit.workspace_wellbeing.repository.EventRepository;
import com.esprit.workspace_wellbeing.repository.UserRepository;

import java.util.List;
import java.util.Optional;


 import org.springframework.stereotype.Component;

@Component 
public class InvitationService implements IinvitationService {
    
    @Autowired
    private InvitationRepository invitationRepository;
  

    @Autowired
    private UserRepository userRepository;

    

    @Autowired
    private EventRepository eventRepository;
  
    @Autowired
    private JavaMailSender mailSender;
  
@Override
    public Invitation addInvitation(Invitation invitation,String receiver_username,String sender_username,Long event_id) {
        invitation.setReceiver_user(userRepository.findByUsername(receiver_username).get());
        invitation.setSender_user(userRepository.findByUsername(sender_username).get());
        invitation.setEvent(eventRepository.findById(event_id).get());
        
        String receiver_mail= userRepository.findByUsername(receiver_username).get().getMail();
        String sender_mail= userRepository.findByUsername(sender_username).get().getMail();
        
        SimpleMailMessage message= new SimpleMailMessage();
        message.setFrom(sender_mail);
        message.setTo(receiver_mail);
        message.setSubject("Invitation to:"+invitation.getEvent().getTitle() );
        message.setText(invitation.getEvent().toString());
        
        mailSender.send(message);
        
        return invitationRepository.save(invitation);
    }
    
@Override
    public Optional<Invitation> getInvitationById(Long id) {
        return invitationRepository.findById(id); 
    }

@Override
public List<Invitation> existInvitationToSender(Long event_id, Long sender_id) {
	return invitationRepository.existInvitationToSender(event_id, sender_id);
}


}
