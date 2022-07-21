package com.esprit.workspace_wellbeing.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.assertj.ApplicationContextAssertProvider;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.esprit.workspace_wellbeing.entity.Post;
import com.esprit.workspace_wellbeing.repository.PostRepository;
import com.esprit.workspace_wellbeing.repository.UserRepository;
import com.esprit.workspace_wellbeing.security.jwt.response.JwtResponse;
import com.esprit.workspace_wellbeing.security.jwt.response.ResponseMessage;

@Service
public class PostService {
	@Autowired
	private PostRepository postrepo;

	@Autowired
	private UserRepository userRepository;

	public Post createPost(Post post, Long userId) {
		post.setUserPosterId(userRepository.findById(userId).get());
		post.setStatus(0);
		return postrepo.save(post);
	}

	@Autowired
	public List<Post> findAll() {
		return postrepo.findAll();
	}

	public Post findById(long postId) {
		return postrepo.findById(postId).get();
	}

	public Post update(long postId, @Valid Post postRequest) {
		Optional<Post> postOptional = postrepo.findById(postId);
		if (postOptional.isPresent()) {
			Post post = postOptional.get();
			post.setContent(postRequest.getContent());
			post.setTitle(postRequest.getTitle());
			post.setPathFile(postRequest.getPathFile());
			return postrepo.save(post);
		} else
			throw new ResourceNotFoundException("PostId " + postId + " not found");
	}

	public ResponseEntity<?> detele(Long postId) {
		Optional<Post> postOptional = postrepo.findById(postId);
		if (postOptional.isPresent()) {
			Post post = postOptional.get();
			postrepo.delete(post);
			return ResponseEntity.ok().build();

		} else
			throw new ResourceNotFoundException("PostId " + postId + " not found");
	}

	public String showMostRecentPosts() {
		List<Post> listPost = postrepo.findAll().stream().filter(x -> x.getStatus().equals(1))
				.collect(Collectors.toList());
		Collections.reverse(listPost);
		String posts = "";
		for (Post post : listPost) {
			posts = posts + ("Title : " + post.getTitle() + "\n Content : " + post.getContent() + " \n");
			if (post.getPathFile() != null)
				posts = posts + " Tap here to view the attached file : " + post.getPathFile();
			else
				posts = posts + "No attached file to this post";
			posts = posts + "\n";
			posts = posts + "\n";
		}
		return posts;
	}

	public ResponseEntity<?> aprovePost(long postId, long userId) {
		if (userRepository.findById(userId).get().getRoles().stream().filter(x -> x.getName().toString().equals("ROLE_ADMIN")).collect(Collectors.toList()).size()>0) {
			Optional<Post> post = postrepo.findById(postId);
			if (!post.isPresent()) {
				return new ResponseEntity<>(new ResponseMessage("This post does not exists"), HttpStatus.BAD_REQUEST);
			}
			if (post.get().getStatus().equals(1)) {
				return new ResponseEntity<>(new ResponseMessage("This post is already approved"),
						HttpStatus.BAD_REQUEST);
			}
			Post postToApprove = post.get();
			postToApprove.setStatus(1);
			postrepo.save(postToApprove);
			return ResponseEntity.ok("Approved with Succes");
		} else {
			return new ResponseEntity<>(new ResponseMessage("You don't have permession to approve"), HttpStatus.BAD_REQUEST);
		}
	}	

	public ResponseEntity<?> aproveAllPost(long userId) {
		List<Post> listPost = postrepo.findAll().stream().filter(x -> x.getStatus().equals(0))
				.collect(Collectors.toList());
		for (Post post : listPost) {
			this.aprovePost(post.getPostId(), userId);
		}
		return ResponseEntity.ok("All posts were approved with Succes");

	}

}
