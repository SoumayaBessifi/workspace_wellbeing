package com.esprit.workspace_wellbeing.entity;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

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

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Entity
@Data
@Table(name="interaction")
public class Interaction {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interactionId;
	private String commentContent;
	private Integer likeDislike;
	private String updateHistory;
	
	

	public Timestamp getCreationDateTimeStamp() {
		return creationDateTimeStamp;
	}

	public void setCreationDateTimeStamp(Timestamp creationDateTimeStamp) {
		this.creationDateTimeStamp = creationDateTimeStamp;
	}

	public Interaction(Long interactionId, String commentContent, Integer likeDislike, String updateHistory,
			Timestamp creationDateTimeStamp, User userInteracterId, Post postInteraction) {
		super();
		this.interactionId = interactionId;
		this.commentContent = commentContent;
		this.likeDislike = likeDislike;
		this.updateHistory = updateHistory;
		this.creationDateTimeStamp = creationDateTimeStamp;
		this.userInteracterId = userInteracterId;
		this.postInteraction = postInteraction;
	}

	public String getUpdateHistory() {
		return updateHistory;
	}

	public void setUpdateHistory(String updateHistory) {
		this.updateHistory = updateHistory;
	}

	public Post getPostInteraction() {
		return postInteraction;
	}

	public void setPostInteraction(Post postInteraction) {
		this.postInteraction = postInteraction;
	}



	 @CreationTimestamp
	    private Timestamp creationDateTimeStamp;
 	
 	@ManyToOne
 	@JoinColumn (name="userInteracterId")
 	User userInteracterId;
 	
 	@ManyToOne
 	@JoinColumn (name="postInteraction")
 	Post postInteraction;

	public Long getInteractionId() {
		return interactionId;
	}

	public void setInteractionId(Long interactionId) {
		this.interactionId = interactionId;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}

	public Integer getLikeDislike() {
		return likeDislike;
	}

	public void setLikeDislike(Integer likeDislike) {
		this.likeDislike = likeDislike;
	}

	

	public User getUserInteracterId() {
		return userInteracterId;
	}

	public void setUserInteracterId(User userInteracterId) {
		this.userInteracterId = userInteracterId;
	}

	public Post getPost() {
		return postInteraction;
	}

	public void setPost(Post post) {
		this.postInteraction = post;
	}

public Interaction() {
		
	}
	
 	
}
