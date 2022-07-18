package com.esprit.workspace_wellbeing.service;



import org.springframework.beans.factory.annotation.Autowired; 

 import com.esprit.workspace_wellbeing.entity.Activity;
import com.esprit.workspace_wellbeing.repository.ActivityRepository;
import com.esprit.workspace_wellbeing.repository.EventRepository;

import java.util.Optional;


 import org.springframework.stereotype.Component;

@Component 
public class ActivityService {
    

    @Autowired
    private ActivityRepository activityRepository;
  

      @Autowired
    private EventRepository eventRepository;
  

    public Activity addActivity(Activity activity,Long event_id) {
        activity.setEvent(eventRepository.findById(event_id).get());
        return activityRepository.save(activity);
    }
    

    public Optional<Activity> getActivityById(Long id) {
        return activityRepository.findById(id); 
    }

}
