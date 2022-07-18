package com.esprit.workspace_wellbeing.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.esprit.workspace_wellbeing.service.InteractionService;

@Controller
public class InteractionRestAPIs {

	@Autowired
    private InteractionService interactionservice;
}
