package com.esprit.workspace_wellbeing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date ;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Activity implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_activity;
  
    private String name ; 
 
    private String description ;

    private Long duration ;

    private String lieu ; 

 
    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event ; 


}
