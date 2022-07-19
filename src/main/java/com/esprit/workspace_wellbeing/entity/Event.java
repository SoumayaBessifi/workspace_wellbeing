package com.esprit.workspace_wellbeing.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date ;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Event implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_event ;
  
    private String description ;

    private String title ;

    private Date start_date ;

    private Date end_date ;

    private String lieu ;

    private String file ;

	@Override
	public String toString() {
		return "You are kindely invited to the event "+ title + " in "+lieu +" at "+start_date;
	}

    

}
