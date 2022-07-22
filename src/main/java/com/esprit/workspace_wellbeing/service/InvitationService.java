package com.esprit.workspace_wellbeing.service;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.esprit.workspace_wellbeing.entity.Invitation;
import com.esprit.workspace_wellbeing.entity.User;
import com.esprit.workspace_wellbeing.repository.InvitationRepository;
import com.esprit.workspace_wellbeing.repository.EventRepository;
import com.esprit.workspace_wellbeing.repository.UserRepository;

import java.io.File;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
    public Invitation addInvitation(Invitation invitation,String receiver_username,String sender_username,Long event_id) throws MessagingException {
        invitation.setReceiver_user(userRepository.findByUsername(receiver_username).get());
        invitation.setSender_user(userRepository.findByUsername(sender_username).get());
        invitation.setEvent(eventRepository.findById(event_id).get());
        
        String receiver_mail= userRepository.findByUsername(receiver_username).get().getMail();
        String sender_mail= userRepository.findByUsername(sender_username).get().getMail();
        
        String subject= "Invitation to:"+invitation.getEvent().getTitle();
        
        String body= eventRepository.findById(event_id).get().toString();             
        //better on time
        
        /*MimeMessage message = mailSender.createMimeMessage();
        boolean multipart = true;
        MimeMessageHelper helper = new MimeMessageHelper(message, multipart, "utf-8");
        String htmlMsg = "<h3>Im testing send a HTML email</h3>"
                +"<img src='http://www.apache.org/images/asf_logo_wide.gif'>";
        
       message.setContent(htmlMsg, "text/html");
       helper.setFrom(sender_mail);
       helper.setTo(receiver_mail);
       
       helper.setSubject("Invitation to:"+invitation.getEvent().getTitle() );
       
       mailSender.send(message);*/
        
	   sendMailWithAttachement(sender_mail,receiver_mail, body, subject,
			   eventRepository.findById(event_id).get().getEvent_image());
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

public void sendMailWithAttachement(String sender_mail,String receiver_mail,String body,String subject, String attachement) throws MessagingException {
	System.out.println(attachement);
	MimeMessage mimeMessage= mailSender.createMimeMessage();
	MimeMessageHelper messageHelper= new MimeMessageHelper(mimeMessage,true);
	messageHelper.setFrom(sender_mail);
	messageHelper.setTo(receiver_mail);
	messageHelper.setText(body);
	messageHelper.setSubject(subject);
	
	FileSystemResource fileSystemResource= new FileSystemResource(new File(attachement));
	
	messageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);
	mailSender.send(mimeMessage);
	
}

@Override
public int nbrParticipationByEvent(Long event_id, Boolean participate) {
	return invitationRepository.nbrParticipationByEvent(event_id, participate);
}

@Override
public List<?> getParticipants(Long event_id, Boolean participate) {
	return invitationRepository.getParticipants(event_id, participate);
}

}
