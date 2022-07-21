package com.esprit.workspace_wellbeing.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.workspace_wellbeing.entity.Interaction;
import com.esprit.workspace_wellbeing.entity.Post;
import com.esprit.workspace_wellbeing.entity.Reactions;
import com.esprit.workspace_wellbeing.repository.ReactionRepository;
import com.esprit.workspace_wellbeing.service.InteractionService;
import com.esprit.workspace_wellbeing.service.ReactionService;


@RestController
@RequestMapping("/ReactionController")
public class ReactionRestAPIs {

	@Autowired
    private InteractionService interactionservice;
	
	@Autowired
	private ReactionService reactionService;
	
	 @PostMapping("/reaction/{userId}/{postId}") // like comment ?  reacts instead of like // flen falten a aimé ... aminaa
	 
	    public ResponseEntity<?> react(@Valid @RequestBody Reactions react,@PathVariable long userId,@PathVariable long postId) throws Exception{
	       return reactionService.reactToPost(react,userId,postId);
	    }
	
	 @GetMapping("/reaction/{postId}")
	    public List<Reactions> getAllReactionOfPost(@PathVariable long postId) {
	        return reactionService.getAllReactuionsOfPost(postId);
	    }
	 
	 @GetMapping("/reactionsOfUser/{userId}")
	    public List<Reactions> getAllReactionsOfUser(@PathVariable long userId) {
	        return reactionService.getAllReactionsOfSpecificUser(userId);
	    }
	 
	 @PatchMapping("/reaction/{reactionId}")
	    public Reactions updatePost(@PathVariable long reactionId, @Valid @RequestBody Reactions reactionRequest) {
		 		return reactionService.update(reactionId , reactionRequest);
	    }
	 
	 @DeleteMapping("/reaction/{reactionId}")
	    public ResponseEntity<?> deletePost(@PathVariable Long reactionId) {
			return reactionService.detele(reactionId);
		}
}
