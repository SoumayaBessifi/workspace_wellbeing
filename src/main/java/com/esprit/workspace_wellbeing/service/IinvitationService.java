package com.esprit.workspace_wellbeing.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;

import com.esprit.workspace_wellbeing.entity.Invitation;
import com.esprit.workspace_wellbeing.entity.User;

public interface IinvitationService {
	Invitation addInvitation(Invitation invitation,String receiver_username,String sender_username,Long event_id) throws MessagingException, IOException ;
	Optional<Invitation> getInvitationById(Long id);
	List<Invitation> existInvitationToSender(Long event_id,Long sender_id );
    int nbrParticipationByEvent(Long event_id,Boolean participate );
    List<?> getParticipants(Long event_id,Boolean participate);
}
