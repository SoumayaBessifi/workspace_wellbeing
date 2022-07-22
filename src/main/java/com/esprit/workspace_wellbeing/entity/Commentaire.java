 package com.esprit.workspace_wellbeing.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Commentaire implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_commentaire ;
  
	@NotNull(message="commentaire is required")
    @NotEmpty
    private String commentaire ; 
 
    private LocalDateTime date_commentaire ;
    
	@Range(min=0, max=5,message="Note must be between 1 to 5")
    private float note=0 ;
 
    @ManyToOne
    @JoinColumn(name = "id_event")
    private Event event ; 


}
