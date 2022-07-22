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
import com.esprit.workspace_wellbeing.service.InteractionService;


@RestController
@RequestMapping("/InteractionController")
public class InteractionRestAPIs {

	@Autowired
    private InteractionService interactionservice;
	
	 @PostMapping("/comment/{userId}/{postId}") // like comment ?  reacts instead of like
	 
	    public Interaction comment(@Valid @RequestBody Interaction comment,@PathVariable long userId,@PathVariable long postId){
	       return interactionservice.commentPost(comment,userId,postId);
	    }
	
	
	 @GetMapping("/commentsPostAll/{postId}")
	    public List<Interaction> getAllCommentsOfPost(@PathVariable long postId) {
	        return interactionservice.getAllCommentsOfPost(postId);
	    }
	 
	 @GetMapping("/commentsUserAll/{userId}")
	    public List<Interaction> getAllcommentOfuser(@PathVariable long userId) {
	        return interactionservice.getAllCommentOfUser(userId);
	    }
	 
	 
	 @PatchMapping("/comment/{interactionId}")
	    public Interaction updatePost(@PathVariable long interactionId, @Valid @RequestBody Interaction interactionRequest) {
		 		return interactionservice.update(interactionId , interactionRequest);
	    }
	
	 @DeleteMapping("/comment/{commentId}")
	    public ResponseEntity<?> deletePost(@PathVariable Long commentId) {
			return interactionservice.detele(commentId);
		}
}
