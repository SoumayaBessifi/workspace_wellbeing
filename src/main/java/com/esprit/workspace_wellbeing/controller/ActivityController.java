package com.esprit.workspace_wellbeing.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esprit.workspace_wellbeing.entity.Activity;
import com.esprit.workspace_wellbeing.entity.Event;
import com.esprit.workspace_wellbeing.repository.ActivityRepository;
 import com.esprit.workspace_wellbeing.repository.EventRepository;
import com.esprit.workspace_wellbeing.service.ActivityService;
 
import java.util.List;
import java.util.Optional;
import java.util.Date ;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/application") 
public class ActivityController {

    @Autowired
    private EventRepository eventRepository;


    @Autowired
    private ActivityRepository activityRepository;



    @Autowired
    private ActivityService activityService;

     
    @GetMapping("/activitys")
    public ResponseEntity<List<Activity>> GetAllActivity() {
        List<Activity> listActivity = null;
        listActivity = activityRepository.findAll();
        if (listActivity.isEmpty())
            return ResponseEntity.noContent().build();

        return new ResponseEntity<>(listActivity, HttpStatus.OK);
    }
 
     
    @GetMapping("/activitys/{activity_Id}")
    public ResponseEntity<Optional<Activity>> GetActivityById(@PathVariable long activity_Id) {
        Optional<Activity> listActivity;
        listActivity = activityService.getActivityById(activity_Id);

        if (!listActivity.isPresent())
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(listActivity, HttpStatus.OK);

    }
 
    
    @GetMapping("/activitysbyevent/{event_id}")
    public ResponseEntity<List<Activity>> GetActivityByEventId(@PathVariable long event_id) {
        List<Activity> listActivity; 
        Event event = eventRepository.findById(event_id).get(); 
        listActivity = activityRepository.findActivitysByEvent(event);  
        return new ResponseEntity<>(listActivity, HttpStatus.OK);

    }
    

    @PutMapping("/activitys/{activity_Id}")
    public ResponseEntity<Activity> pullActivity(@PathVariable long activity_Id,@RequestBody Activity event) {
        
        Optional<Activity> activityData = activityService.getActivityById(activity_Id);
        
         if(activityData.isPresent()){
            Activity activityLocal = activityData.get();
            activityLocal.setName(event.getName());
            activityLocal.setDescription(event.getDescription());
            activityLocal.setDuration(event.getDuration());
            activityLocal.setLieu(event.getLieu()); 
                return new ResponseEntity<>(activityRepository.save(activityLocal),HttpStatus.OK);
         }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

    }

 
      
   
    @PostMapping("/activitys/{event_id}")
    public ResponseEntity<Activity> addCommentaire(@RequestBody Activity calendar, @PathVariable long event_id) {
        Activity activityLocal = activityService.addActivity(calendar, event_id);

        if (activityLocal == null)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(activityLocal, HttpStatus.OK);

    }


    @DeleteMapping("/activitys/{activity_Id}")
    public ResponseEntity<Activity> deleteActivity(@PathVariable long activity_Id) {
        activityRepository.deleteById(activity_Id);
        return ResponseEntity.accepted().build();
    }


    
}
