package com.esprit.workspace_wellbeing.controller;

import java.awt.print.Pageable;
import java.util.Collections;
import java.util.List;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.workspace_wellbeing.entity.Post;
import com.esprit.workspace_wellbeing.service.PostService;



@RestController
@RequestMapping("/PostController")

public class PostRestAPIs {
	@Autowired
    private PostService postservice;
	
	 @PostMapping("/addPost/{userId}")
	 
	    public Post addPost(@Valid @RequestBody Post post,@PathVariable long userId){
	       return postservice.createPost(post,userId);
	    }
	
	 @GetMapping("/posts")
	    public List<Post> getAllPosts() {
	        return postservice.findAll();
	    }
	 
	 @GetMapping("/posts/mostRecentPosts")
	    public String showMostRecentPosts() {
	        return postservice.showMostRecentPosts();
	    }
	 
	 
	 
	 
	@PatchMapping("/posts/{postId}")
	    public Post updatePost(@PathVariable long postId, @Valid @RequestBody Post postRequest) {
		 		return postservice.update(postId , postRequest);
	    }
	
	@PatchMapping("/postsApprove/{postId}/{userId}")
    public ResponseEntity<?> approvePost(@PathVariable long postId,@PathVariable long userId) {
	 		return postservice.aprovePost(postId , userId);
    }
	
	@PatchMapping("/postsApproveAll/{userId}")
	public ResponseEntity<?> approveAllPost(@PathVariable long userId) {
		return postservice.aproveAllPost(userId);
	}
	
	@DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
		return postservice.detele(postId);
	}
	 
}
