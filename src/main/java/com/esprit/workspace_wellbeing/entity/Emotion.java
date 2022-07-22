package com.esprit.workspace_wellbeing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.esprit.workspace_wellbeing.enums.State;

@Entity
@Table(name = "emotion")
public class Emotion {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_emotion;
	@ManyToOne  
	@JoinColumn(name="creator_id")
	private User user;
	
	@Enumerated(EnumType.STRING)
	@NotNull
	private State state;
	
	@Column(columnDefinition = "TEXT")
	private String text;
	
	public int getId() {
	        return id_emotion;
	    }

	    public void setId(int id) {
	        this.id_emotion = id;
	    }

	    public String getText() {
	        return text;
	    }

	    public void setText(String answer) {
	        this.text = answer;
	    }

	    public User getUser() {
	        return user;
	    }

	    public void setUser(User user) {
	        this.user = user;
	    }
	    public State getState() {
	        return state;
	    }
	    
}
