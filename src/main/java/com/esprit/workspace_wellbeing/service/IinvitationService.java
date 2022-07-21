package com.esprit.workspace_wellbeing.service;

import java.util.List;
import java.util.Optional;

import com.esprit.workspace_wellbeing.entity.Invitation;

public interface IinvitationService {
	Invitation addInvitation(Invitation invitation,String receiver_username,String sender_username,Long event_id) ;
	Optional<Invitation> getInvitationById(Long id);
	List<Invitation> existInvitationToSender(Long event_id,Long sender_id );
}
