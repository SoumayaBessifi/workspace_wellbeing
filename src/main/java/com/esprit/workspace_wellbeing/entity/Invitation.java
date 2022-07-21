 
package com.esprit.workspace_wellbeing.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


import java.io.Serializable;


@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Invitation implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_invitation ;

    private Boolean favorit=false ;


    private Boolean participate=false;

    @ManyToOne
    @JoinColumn(name = "receiver_user")
    private User receiver_user ; 
  
    @ManyToOne
    @JoinColumn(name = "sender_user")
    private User sender_user ; 

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event ; 



}
