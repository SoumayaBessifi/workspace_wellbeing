package com.esprit.workspace_wellbeing.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.esprit.workspace_wellbeing.entity.Invitation;
import com.esprit.workspace_wellbeing.entity.Notification;

@RepositoryRestResource(path = "event", collectionResourceRel = "event")
public interface NotificationRepository  extends JpaRepository<Notification,Long> {
    
    public List<Notification> findNotificationsByInvitation(Invitation event);

}
