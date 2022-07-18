package com.esprit.workspace_wellbeing.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.esprit.workspace_wellbeing.entity.Post;
import com.esprit.workspace_wellbeing.repository.PostRepository;
import com.esprit.workspace_wellbeing.repository.UserRepository;

@Service
public class PostService {
	@Autowired
    private PostRepository postrepo;
	
	@Autowired
	private UserRepository userRepository;
	 public Post createPost(Post post,Long userId) {
		 	post.setUserPosterId(userRepository.findById(userId).get());
	        return postrepo.save(post);
	    }
	 @Autowired
	public  List<Post> findAll() {
		return  postrepo.findAll();
	}

	 
	 
	public Post findById(long postId) {
		return postrepo.findById(postId).get();
	}
	
	
	public Post update(long postId, @Valid Post postRequest) {
		Optional<Post> postOptional = postrepo.findById(postId);
		if(postOptional.isPresent()) {
			Post post = postOptional.get();
			post.setContent(postRequest.getContent());
			post.setTitle(postRequest.getTitle());
			post.setPathFile(postRequest.getPathFile());
	        return postrepo.save(post);
		}
		else throw new  ResourceNotFoundException("PostId " + postId + " not found");
	}
	public ResponseEntity<?> detele(Long postId) {
		Optional<Post> postOptional = postrepo.findById(postId);
		if(postOptional.isPresent()) {
			Post post = postOptional.get();
			postrepo.delete(post);
            return ResponseEntity.ok().build();

		}
		else throw new ResourceNotFoundException("PostId " + postId + " not found");
	}
	
	
	
	 
}
