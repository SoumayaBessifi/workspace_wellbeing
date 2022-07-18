package com.esprit.workspace_wellbeing.service;

import java.util.List;

import com.esprit.workspace_wellbeing.entity.Offre;


public interface IOffreService {

	public Offre addOffre(Offre o, String collaboration_name);
	public List<Offre> findAllOffre();
	public void deleteOffre(Long id);
	public Offre getOffre(Long id);
	public Offre updateOffre(Offre o);


}
