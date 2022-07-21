package com.esprit.workspace_wellbeing.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.Data;

@Entity
@Data
@Table(name = "post")
public class Post {

	@JsonCreator
		public Post() {
	}

		public Long getPostId() {
			return postId;
		}

		public void setPostId(Long postId) {
			this.postId = postId;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}


		public Timestamp getCreationDateTimeStamp() {
			return creationDateTimeStamp;
		}

		public void setCreationDateTimeStamp(Timestamp creationDateTimeStamp) {
			this.creationDateTimeStamp = creationDateTimeStamp;
		}

		public List<Interaction> getPostInteractions() {
			return postInteractions;
		}

		public void setPostInteractions(List<Interaction> postInteractions) {
			this.postInteractions = postInteractions;
		}

		public List<Reactions> getPostReaction() {
			return postReaction;
		}

		public void setPostReaction(List<Reactions> postReaction) {
			this.postReaction = postReaction;
		}

		

		public Post(Long postId, @NotNull @Size(max = 100) String title, @NotBlank String content, Integer status,
				Timestamp creationDateTimeStamp, String pathFile, User userPosterId, List<Interaction> postInteractions,
				List<Reactions> postReaction) {
			super();
			this.postId = postId;
			this.title = title;
			this.content = content;
			this.status = status;
			this.creationDateTimeStamp = creationDateTimeStamp;
			this.pathFile = pathFile;
			this.userPosterId = userPosterId;
			this.postInteractions = postInteractions;
			this.postReaction = postReaction;
		}

		public String getPathFile() {
			return pathFile;
		}

		public void setPathFile(String pathFile) {
			this.pathFile = pathFile;
		}

		public User getUserPosterId() {
			return userPosterId;
		}

		public void setUserPosterId(User userPosterId) {
			this.userPosterId = userPosterId;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long postId;
	 
	 	@NotNull
	    @Size(max = 100)
	    private String title;
	 
	 	@NotBlank
	 	private String content;
	 	
	 	private Integer status;
	 
	 	 @CreationTimestamp
		    private Timestamp creationDateTimeStamp;
	 	
	 	private String pathFile;
	 	
	 	@ManyToOne 
	 	@JoinColumn (name="userPosterId")
	 	User userPosterId;
	 	
	 	 @OneToMany(mappedBy = "postInteraction", cascade = CascadeType.ALL)
	     private List<Interaction> postInteractions;
	 	 
	 	 @OneToMany(mappedBy = "postReaction", cascade = CascadeType.ALL)
	 	 private List<Reactions> postReaction;

}
