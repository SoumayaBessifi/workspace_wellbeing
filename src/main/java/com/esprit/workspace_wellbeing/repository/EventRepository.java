package com.esprit.workspace_wellbeing.repository;
 
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

 import com.esprit.workspace_wellbeing.entity.Event;

@RepositoryRestResource(path = "event", collectionResourceRel = "event")
public interface EventRepository extends JpaRepository<Event,Long> {
    
}
