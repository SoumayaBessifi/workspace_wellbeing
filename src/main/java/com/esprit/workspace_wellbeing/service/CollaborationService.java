package com.esprit.workspace_wellbeing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.esprit.workspace_wellbeing.entity.Collaboration;
import com.esprit.workspace_wellbeing.entity.Offre;
import com.esprit.workspace_wellbeing.entity.Post;
import com.esprit.workspace_wellbeing.repository.CollaborationRepository;
import com.esprit.workspace_wellbeing.repository.OffreRepository;
import com.esprit.workspace_wellbeing.repository.UserRepository;
import com.esprit.workspace_wellbeing.security.jwt.response.ResponseMessage;

@Service
public class CollaborationService implements ICollaborationService {

	@Autowired
	CollaborationRepository collaborationRepository;

	@Autowired
	OffreRepository offreRepository;
	
	@Autowired
	UserRepository userRepository;

	@Override
	public Collaboration addCollaboration(Collaboration collaboration, Long user_id) {
		// TODO Auto-generated method stub
		collaboration.setUser(userRepository.findById(user_id).get());
		return collaborationRepository.save(collaboration);
	}

	@Override
	public List<Collaboration> findAllCollaboration() {
		List<Collaboration> collaboration= (List<Collaboration>) collaborationRepository.findAll();
		for(Collaboration c:collaboration) {
		}
		return (List<Collaboration>) collaborationRepository.findAll();
	}

	@Override
	public void deleteCollaboration(Long id) {
		
		collaborationRepository.deleteById(id);

	}
	

	@Override
	public Collaboration getCollaboration(Long id) {
		
		return collaborationRepository.findById(id).orElse(null);
	}

	@Override
	public Collaboration updateCollaboration(Collaboration c ,Long collaborationId) {
		Optional<Collaboration>  collab = collaborationRepository.findById(collaborationId);
		if(collab.isPresent()) {
			Collaboration collaboration = collab.get();
			collaboration.setCollaborationName(c.getCollaborationName());
			collaboration.setDate_collaboration(c.getDate_collaboration());
			collaboration.setOffre(c.getOffre());

		return collaborationRepository.save(collaboration);
		}
		else throw new  ResourceNotFoundException("collaborationId " + collaborationId + " not found");
		

	}
	

	@Override
	public void assignOffreToCollaboration(Long id_Offre, Long id_Collaboration) {
		Collaboration collab = collaborationRepository.findById(id_Collaboration).get();
		Offre off= offreRepository.findById(id_Offre).get();
		off.setCollaboration(collab);
		offreRepository.save(off);
	}

	

}
