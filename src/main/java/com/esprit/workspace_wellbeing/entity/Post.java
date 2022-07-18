package com.esprit.workspace_wellbeing.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

		public LocalDateTime getCreationDate() {
			return creationDate;
		}

		public void setCreationDate(@NotBlank LocalDateTime creationDate) {
			this.creationDate = creationDate;
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

		public Post(Long postId, @NotNull @Size(max = 100) String title, @NotBlank String content,
			@NotBlank @NotBlank LocalDateTime creationDate, String pathFile, User userPosterId) {
		super();
		this.postId = postId;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
		this.pathFile = pathFile;
		this.userPosterId = userPosterId;
	}

		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long postId;
	 
	 	@NotNull
	    @Size(max = 100)
	    private String title;
	 
	 	@NotBlank
	 	private String content;
	 
	 	@NotBlank
	 	@CreationTimestamp
	 	private LocalDateTime creationDate;
	 	
	 	private String pathFile;
	 	
	 	@ManyToOne 
	 	@JoinColumn (name="userPosterId")
	 	User userPosterId;

}
