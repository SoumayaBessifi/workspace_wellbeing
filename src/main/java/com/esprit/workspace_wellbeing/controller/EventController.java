package com.esprit.workspace_wellbeing.controller;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esprit.workspace_wellbeing.entity.Event;
import com.esprit.workspace_wellbeing.repository.EventRepository;
import com.esprit.workspace_wellbeing.service.EventService;

import java.util.List;
import java.util.Optional;

import java.util.List;
import java.util.Optional;

 
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/application")
public class EventController {


    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private  EventService eventService;

    @GetMapping("/events")
    public ResponseEntity<List<Event>> GetAllEvents() {
        List<Event> listEvent = null;
        listEvent = eventRepository.findAll();
        if (listEvent.isEmpty())
            return ResponseEntity.noContent().build();

        return new ResponseEntity<>(listEvent, HttpStatus.OK);
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<Optional<Event>> retreiveCategoryById(@PathVariable long eventId) {
        Optional<Event> event;
        event = eventService.getEventById(eventId);

        if (!event.isPresent())
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(event, HttpStatus.OK);

    }

    @PutMapping("/events/{eventId}")
    public ResponseEntity<Event> pullEvent(@PathVariable long eventId,@RequestBody Event event) {
        
        Optional<Event> eventData = eventService.getEventById(eventId);
        
         if(eventData.isPresent()){
                Event eventLocal = eventData.get();
                eventLocal.setTitle(event.getTitle());
                eventLocal.setDescription(event.getDescription());
                eventLocal.setFile(event.getFile());
                eventLocal.setLieu(event.getLieu()); 
                eventLocal.setStart_date(event.getStart_date());
                eventLocal.setEnd_date(event.getEnd_date()); 
                return new ResponseEntity<>(eventRepository.save(eventLocal),HttpStatus.OK);
         }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

    }

 

    @PostMapping("/events")
    public ResponseEntity<Event> addEvent(@RequestBody Event company) {
        Event eventLocal = null;
        eventLocal = eventRepository.save(company);

        if (eventLocal == null)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(eventLocal, HttpStatus.OK);

    }


    @DeleteMapping("/events/{eventId}")
    public ResponseEntity<Event> deleteEvent(@PathVariable long eventId) {
        eventRepository.deleteById(eventId);
        return ResponseEntity.accepted().build();
    }





    
}
