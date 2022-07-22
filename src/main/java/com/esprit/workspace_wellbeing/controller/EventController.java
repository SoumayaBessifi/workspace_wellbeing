package com.esprit.workspace_wellbeing.controller;

 
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esprit.workspace_wellbeing.entity.Event;
import com.esprit.workspace_wellbeing.entity.EventDto;
import com.esprit.workspace_wellbeing.entity.Offre;
import com.esprit.workspace_wellbeing.entity.OffreDto;
import com.esprit.workspace_wellbeing.repository.EventRepository;
import com.esprit.workspace_wellbeing.service.EventService;
import com.esprit.workspace_wellbeing.utilities.FileUtilities;

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
    @Autowired
	ModelMapper modelMapper;
    
	@RequestMapping(value="/events", method= RequestMethod.POST, headers = "Accept=application/json",consumes= org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE, produces=org.springframework.http.MediaType.MULTIPART_FORM_DATA_VALUE)
 	public Event addEvent(@RequestBody @ModelAttribute EventDto eventDto) {
		Event event = modelMapper.map(eventDto, Event.class);
		String imageFileName = eventDto.getEvent_image().getOriginalFilename();
		FileUtilities.saveArticleImage(imageFileName, eventDto.getEvent_image());
		event.setEvent_image("C:/images/offres/" + imageFileName);
		return eventRepository.save(event);
		}

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
                eventLocal.setLieu(event.getLieu()); 
                eventLocal.setStart_date(event.getStart_date());
                eventLocal.setEnd_date(event.getEnd_date()); 
                return new ResponseEntity<>(eventRepository.save(eventLocal),HttpStatus.OK);
         }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

    }

    @DeleteMapping("/events/{eventId}")
    public ResponseEntity<Event> deleteEvent(@PathVariable long eventId) {
        eventRepository.deleteById(eventId);
        return ResponseEntity.accepted().build();
    }





    
}
