package com.esprit.workspace_wellbeing.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


@Table(name = "Collaboration")

public class Collaboration implements Serializable{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_Collaboration")
		private Long id_Collaboration;
		@NotNull(message ="Collaboration Name is required")
        @NotEmpty
		private String collaborationName;
		@DateTimeFormat(pattern="dd/mm/yyyy")
        @NotNull(message="dateCollaboration is required")
		private Date date_collaboration;
		@OneToMany(targetEntity = Offre.class, cascade = CascadeType.ALL)
		@JoinColumn(name = "coll_fk", referencedColumnName = "id_Collaboration")
		    private Set<Offre> offre;
		
		
		@ManyToOne  
		@JoinColumn(name="creator_id")
		private User user;
			
		
}
		
