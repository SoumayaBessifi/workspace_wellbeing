package com.esprit.workspace_wellbeing.entity;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.io.Serializable;
import java.util.Date;



 public class OffreDto implements Serializable{


		private Long id_Offre;
		private String offre_name;
		private String description;
		private MultipartFile image;
 		private Date date_offre;
 		private Date date_expiration;
		private Boolean reserve=false;
 		private int rate ;
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
		public MultipartFile getImage() {
			return image;
		}
		public void setImage(MultipartFile image) {
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
