package com.esprit.workspace_wellbeing.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.workspace_wellbeing.entity.Offre;
import com.esprit.workspace_wellbeing.repository.CollaborationRepository;
import com.esprit.workspace_wellbeing.repository.OffreRepository;

@Service
public class OffreService implements IOffreService {

	@Autowired
	OffreRepository offreRepository;

	@Autowired 
	CollaborationRepository collaborationRepository;
	@Override
	public Offre addOffre(Offre o, String collaboration_name) {
		o.setCollaboration(collaborationRepository.findByCollaborationName(collaboration_name).get());
		return offreRepository.save(o);
	}

	@Override
	public List<Offre> findAllOffre() {
		List<Offre> offre= (List<Offre>) offreRepository.findAll();
		for(Offre o:offre) {
		}
		return (List<Offre>) offreRepository.findAll();
	}

	@Override
	public void deleteOffre(Long id) {
		offreRepository.deleteById(id);
		
	}

	@Override
	public Offre getOffre(Long id) {
		return offreRepository.findById(id).orElse(null);
	}

	@Override
	public Offre updateOffre(Offre o) {
		return offreRepository.save(o);
	}
	
	@Override
	public List<Offre> findAllOffreByCollaborationName(String col) {
		return offreRepository.findByCollaboration(col);

	}


	

}
