package com.esprit.workspace_wellbeing.entity;
import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name = "Offre")
 public class Offre implements Serializable{

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "id_Offre")
		private Long id_Offre;
		@NotNull(message="Offre Name is required")
	    @NotEmpty
		private String offre_name;
        @NotNull(message="description is required")
        @NotEmpty
		private String description;
		private String image;
		@DateTimeFormat(pattern="dd/mm/yyyy")
        @NotNull(message="dateOffre is required")
	    private Date date_offre;
		@DateTimeFormat(pattern="dd/mm/yyyy")
        @NotNull(message="dateExpiration is required")
		private Date date_expiration;
		private Boolean reserve=false;
		@Range(min=1, max=5,message="Rate must be between 1 to 5")
		private int rate ;
		
		@ManyToOne
		@JoinColumn(name="collaboration_name")
		Collaboration collaboration;
		
		public Long getId_Offre() {
			return id_Offre;
		}
		public void setId_Offre(Long id_Offre) {
			this.id_Offre = id_Offre;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getImage() {
			return image;
		}
		public void setImage(String image) {
			this.image = image;
		}
		public Date getDate_offre() {
			return date_offre;
		}
		public void setDate_offre(Date date_offre) {
			this.date_offre = date_offre;
		}
		public Date getDate_expiration() {
			return date_expiration;
		}
		public void setDate_expiration(Date date_expiration) {
			this.date_expiration = date_expiration;
		}
		public Boolean getReserve() {
			return reserve;
		}
		public void setReserve(Boolean reserve) {
			this.reserve = reserve;
		}
		public Collaboration getCollaboration() {
			return collaboration;
		}
		public void setCollaboration(Collaboration collaboration) {
			this.collaboration = collaboration;
		}
		public int getRate() {
			return rate;
		}
		public void setRate(int rate) {
			this.rate = rate;
		}
		public String getOffre_name() {
			return offre_name;
		}
		public void setOffre_name(String offre_name) {
			this.offre_name = offre_name;
		}
	
		

}
