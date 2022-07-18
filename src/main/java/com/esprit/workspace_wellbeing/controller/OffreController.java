package com.esprit.workspace_wellbeing.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.workspace_wellbeing.entity.Collaboration;
import com.esprit.workspace_wellbeing.entity.Offre;
import com.esprit.workspace_wellbeing.service.ICollaborationService;
import com.esprit.workspace_wellbeing.service.IOffreService;

@RestController
@RequestMapping("/api")
public class OffreController {

	
	@Autowired
	IOffreService iOffreService;
	
	
	
	@PostMapping("/addOffre/{collaboration_name}")
	@ResponseBody
	public Offre addOffre(@RequestBody Offre o, @PathVariable String collaboration_name) {
		
		return iOffreService.addOffre(o,collaboration_name);
		}
	
	@DeleteMapping("/deleteOffre/{offreId}")
	@ResponseBody
	public void deleteOffre(@PathVariable("offreId") Long id) {
		iOffreService.deleteOffre(id);
	}

	
	@GetMapping("/findAllOffre")
	@ResponseBody
	public List<Offre> findAllCollaboration() {
	List<Offre> listOffre = iOffreService.findAllOffre();
	return listOffre;
	}
	
	@GetMapping("/findOfferById/{offreId}")
	@ResponseBody
	public Offre getOffreById (@PathVariable("offreId") Long offreId) {
	return iOffreService.getOffre(offreId);
	}
	@PutMapping("/updateOffre")
	@ResponseBody 
	public Offre updateOffre(@RequestBody Offre offre) {
	return iOffreService.updateOffre(offre);
	}
	
	
	
}
