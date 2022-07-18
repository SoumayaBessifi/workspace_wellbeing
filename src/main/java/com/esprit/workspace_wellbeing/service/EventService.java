package com.esprit.workspace_wellbeing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.esprit.workspace_wellbeing.entity.Event;
import com.esprit.workspace_wellbeing.repository.EventRepository;


import java.util.Optional;


@Component 
public class EventService {
    
   

    @Autowired
    private EventRepository eventRepository;
  

    public Event addStep(Event calendar) {
        return eventRepository.save(calendar);
    }
    
    public Optional<Event> getEventById(Long id) {
        return eventRepository.findById(id); 
    }


    
}
