package com.esprit.workspace_wellbeing.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.workspace_wellbeing.entity.Collaboration;
import com.esprit.workspace_wellbeing.entity.Offre;
import com.esprit.workspace_wellbeing.entity.OffreDto;
import com.esprit.workspace_wellbeing.service.ICollaborationService;
import com.esprit.workspace_wellbeing.service.IOffreService;
import com.esprit.workspace_wellbeing.utilities.FileUtilities;

@RestController
@RequestMapping("/api")
public class OffreController {

	
	@Autowired
	IOffreService iOffreService;
	
	

@Autowired
	ModelMapper modelMapper;
	@RequestMapping(value="/addOffre/{collaboration_name}", method= RequestMethod.POST, headers = "Accept=application/json",consumes= org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE, produces=org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
 	public Offre addOffre(@RequestBody @ModelAttribute OffreDto offreDto, @PathVariable String collaboration_name) {
		Offre offre = modelMapper.map(offreDto, Offre.class);
		//génerer le nom de l'image
		String imageFileName = offreDto.getImage().getOriginalFilename();
		// sauvgarde de l'image
		FileUtilities.saveArticleImage(imageFileName, offreDto.getImage());
		// sauvgarde le path de la base de donnée
		offre.setImage("C:/images/offres/" + imageFileName);
		return iOffreService.addOffre(offre,collaboration_name);
		}
	

	@GetMapping("/finOffreByCollaborationName/{col}")
	@ResponseBody
	public List<Offre> finOffreByCollaborationName (@PathVariable("col") String col) {
	return iOffreService.findAllOffreByCollaborationName(col);
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
