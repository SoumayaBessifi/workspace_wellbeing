package com.esprit.workspace_wellbeing.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.esprit.workspace_wellbeing.entity.Activity;
import com.esprit.workspace_wellbeing.entity.Event;

@RepositoryRestResource(path = "activity", collectionResourceRel = "activity")
public interface ActivityRepository extends JpaRepository<Activity,Long> {
    
    public List<Activity> findActivitysByEvent(Event event);
 
}
