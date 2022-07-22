 
package com.esprit.workspace_wellbeing.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Notification implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_notification;
  
    private String text ; 
   
  
    @ManyToOne
    @JoinColumn(name = "id_invitation")
    private Invitation invitation ; 


}
