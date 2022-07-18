package com.esprit.workspace_wellbeing.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
@Table(name="interaction")
public class Interaction {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interactionId;
	
	private Integer likeDislike;
	
 	@Temporal (TemporalType.DATE)
 	private Date creationDate;
 	
 	@ManyToOne
 	User userInteracterId;
 	
}
