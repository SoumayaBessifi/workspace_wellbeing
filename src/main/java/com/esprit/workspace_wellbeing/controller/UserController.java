package com.esprit.workspace_wellbeing.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.esprit.workspace_wellbeing.repository.UserRepository;
import com.esprit.workspace_wellbeing.security.jwt.request.SignUpForm;
import com.esprit.workspace_wellbeing.service.UserDetailsServiceImpl;
import com.esprit.workspace_wellbeing.entity.User;
import com.esprit.workspace_wellbeing.repository.EventRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserDetailsServiceImpl userService;

	@DeleteMapping("/{username}")
	public ResponseEntity<String> deleteUser(@PathVariable String username) {
		try {
			 userService.deleteByUsername(username);
			return new ResponseEntity<>("User Deleted", HttpStatus.OK);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>("User not found !", HttpStatus.NOT_FOUND);
		}

	}

	@PutMapping
	public ResponseEntity<?> updateUser(@Valid @RequestBody SignUpForm userToUpdate) {
		User user;
		try {
			user = userService.updateUser(userToUpdate);
		} catch (NotFoundException e) {
			e.printStackTrace();
			return new ResponseEntity<>("User not found !", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(user, HttpStatus.OK);
	}

}
