package com.esprit.workspace_wellbeing.service;




import org.springframework.beans.factory.annotation.Autowired;

import com.esprit.workspace_wellbeing.entity.Notification;
import com.esprit.workspace_wellbeing.repository.EventRepository;
import com.esprit.workspace_wellbeing.repository.InvitationRepository;
import com.esprit.workspace_wellbeing.repository.NotificationRepository;

import java.util.Optional;


 import org.springframework.stereotype.Component;

@Component 
public class NotificationService {
    
    @Autowired
    private NotificationRepository notificationRepository;
  

      @Autowired
    private InvitationRepository invitationRepository;
  

    public Notification addNotification(Notification notification,Long event_id) {
        notification.setInvitation(invitationRepository.findById(event_id).get());

        return notificationRepository.save(notification);
    }
    

    public Optional<Notification> getNotificationById(Long id) {
        return notificationRepository.findById(id); 
    }

}
