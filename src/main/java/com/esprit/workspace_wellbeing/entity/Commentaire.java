 package com.esprit.workspace_wellbeing.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date ;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Commentaire implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_commentaire ;
  
    private String commentaire ; 
 
    private Date date_commentaire ;

    private String note ;
 
    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event ; 


}
