package com.esprit.workspace_wellbeing.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.esprit.workspace_wellbeing.entity.Invitation;
import com.esprit.workspace_wellbeing.entity.Notification;
import com.esprit.workspace_wellbeing.repository.NotificationRepository;
import com.esprit.workspace_wellbeing.service.InvitationService;
import com.esprit.workspace_wellbeing.service.NotificationService;

import java.util.List;
import java.util.Optional;
import java.util.Date ;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/application") 
public class NotificationController {

    

    @Autowired
    private InvitationService invitationService;
 
    @Autowired
    private NotificationRepository notificationRepository;
 
    @Autowired
    private NotificationService notificationService;

     
    @GetMapping("/notifications")
    public ResponseEntity<List<Notification>> GetAllNotification() {
        List<Notification> listNotification = null;
        listNotification = notificationRepository.findAll();
        if (listNotification.isEmpty())
            return ResponseEntity.noContent().build();

        return new ResponseEntity<>(listNotification, HttpStatus.OK);
    }
 
     
    @GetMapping("/notifications/{notification_Id}")
    public ResponseEntity<Optional<Notification>> GetNotificationById(@PathVariable long notification_Id) {
        Optional<Notification> listNotification;
        listNotification = notificationService.getNotificationById(notification_Id);

        if (!listNotification.isPresent())
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(listNotification, HttpStatus.OK);

    }
 
    
    @GetMapping("/notificationsbyinvitation/{event_id}")
    public ResponseEntity<List<Notification>> GetNotificationByEventId(@PathVariable long invitation_id) {
        List<Notification> listNotification; 
        Invitation invitation = invitationService.getInvitationById(invitation_id).get(); 
        listNotification = notificationRepository.findNotificationsByInvitation(invitation);  
        return new ResponseEntity<>(listNotification, HttpStatus.OK);

    }
    

    @PutMapping("/notifications/{notification_Id}")
    public ResponseEntity<Notification> pullNotification(@PathVariable long notification_Id,@RequestBody Notification notification) {
        
        Optional<Notification> notificationData = notificationService.getNotificationById(notification_Id);
        
         if(notificationData.isPresent()){
            Notification notificationLocal = notificationData.get();
            notificationLocal.setText(notification.getText());
            
                return new ResponseEntity<>(notificationRepository.save(notificationLocal),HttpStatus.OK);
         }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
         }

    }
 
    @PostMapping("/notifications/{event_id}")
    public ResponseEntity<Notification> addNotification(@RequestBody Notification notification, @PathVariable long event_id) {
        Notification notificationLocal = notificationService.addNotification(notification, event_id);

        if (notificationLocal == null)
            return ResponseEntity.noContent().build();
        return new ResponseEntity<>(notificationLocal, HttpStatus.OK);
    }


    @DeleteMapping("/notifications/{notification_Id}")
    public ResponseEntity<Notification> deleteNotification(@PathVariable long notification_Id) {
        notificationRepository.deleteById(notification_Id);
        return ResponseEntity.accepted().build();
    }


    
}
