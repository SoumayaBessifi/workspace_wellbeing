package com.esprit.workspace_wellbeing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.workspace_wellbeing.repository.InteractionRepository;

@Service
public class InteractionService {
	@Autowired
    private InteractionRepository interRepo;


}
