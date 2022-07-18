package com.esprit.workspace_wellbeing.service;

import java.util.List;

import com.esprit.workspace_wellbeing.entity.Collaboration;

public interface ICollaborationService {
	public Collaboration addCollaboration(Collaboration c,Long user_id);
	public List<Collaboration> findAllCollaboration();
	public void deleteCollaboration(Long id);
	public Collaboration getCollaboration(Long id);
	public Collaboration updateCollaboration(Collaboration c);
	void  assignOffreToCollaboration(Long id_Offre, Long id_Collaboration);

}


