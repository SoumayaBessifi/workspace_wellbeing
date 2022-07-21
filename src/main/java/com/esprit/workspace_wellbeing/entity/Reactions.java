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
@Table(name="reactions")
public class Reactions {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ReactionId;
	private Integer reaction;
	
	 @CreationTimestamp
	    private Timestamp creationDateTime;
 	
 	@ManyToOne
 	@JoinColumn (name="userInteracterId")
 	User userInteracterId;
 	
 	@ManyToOne
 	@JoinColumn (name="postReaction")
 	Post postReaction;

	public Reactions() {
	}

	public Reactions(Long reactionId, Integer reaction, Timestamp creationDateTime, User userInteracterId,
			Post postReaction) {
		super();
		ReactionId = reactionId;
		this.reaction = reaction;
		this.creationDateTime = creationDateTime;
		this.userInteracterId = userInteracterId;
		this.postReaction = postReaction;
	}

	public Timestamp getCreationDateTime() {
		return creationDateTime;
	}

	public void setCreationDateTime(Timestamp creationDateTime) {
		this.creationDateTime = creationDateTime;
	}

	public Long getReactionId() {
		return ReactionId;
	}

	public void setReactionId(Long reactionId) {
		ReactionId = reactionId;
	}

	public Integer getReaction() {
		return reaction;
	}

	public void setReaction(Integer reaction) {
		this.reaction = reaction;
	}

	

	public User getUserInteracterId() {
		return userInteracterId;
	}

	public void setUserInteracterId(User userInteracterId) {
		this.userInteracterId = userInteracterId;
	}

	public Post getPostReaction() {
		return postReaction;
	}

	public void setPostReaction(Post postReaction) {
		this.postReaction = postReaction;
	}
 	
 	
}
