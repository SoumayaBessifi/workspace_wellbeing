package com.esprit.workspace_wellbeing.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date ;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Event implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_event ;
  
	@NotNull(message="Description of event is required")
    @NotEmpty
    private String description ;

    private String event_image;

	@NotNull(message="Event title is required")
    @NotEmpty
    private String title ;
	
	@DateTimeFormat(pattern="dd/mm/yyyy")
    @NotNull(message="start date of the event is required")
    private Date start_date ;
	
	@DateTimeFormat(pattern="dd/mm/yyyy")
    private Date end_date ;
    
	private String lieu;

	@Override
	public String toString() {
		return "You are kindely invited to the event "+ title + " in "+lieu +" at "+start_date;
	}

    

}
