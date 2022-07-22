package com.esprit.workspace_wellbeing.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;



import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
@Entity
@Table(name = "quiz")
public class Quiz {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_Quiz;

	@ManyToOne(targetEntity = QuizInteraction.class, cascade = CascadeType.ALL)
	@JoinColumn( referencedColumnName = "id_Quiz")
	    private Set<QuizInteraction> quizInteraction;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="creator_id")
    private User user;
    @Column(columnDefinition = "TEXT")
    @NotNull(message="title is required")
    private String title;
    
    @Column(columnDefinition = "TEXT")
	@NotNull(message="Topic is required")
	private String topic;
    
    @Column(columnDefinition = "TEXT")
    @NotNull(message="Content is required")
    private String content;

    @Column(updatable = false, nullable = false)
    private Date creationDate;

    @Column(nullable = false)
    private Date lastUpdateDate;


    @PrePersist
    protected void onCreate() {
        this.creationDate = new Date();
        this.lastUpdateDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        this.lastUpdateDate = new Date();
    }

    public int getId() {
        return id_Quiz;
    }

    public void setId(int id) {
        this.id_Quiz = id;
    }

    public @NotNull(message = "Topic is required") String getTopic() {
        return topic;
    }

    public void setTopic(@NotNull(message = "Topic is required") String topic) {
        this.topic = topic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return content;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

   

   

}
