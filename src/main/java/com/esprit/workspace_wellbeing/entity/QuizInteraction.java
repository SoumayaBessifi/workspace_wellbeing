package com.esprit.workspace_wellbeing.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Retry.Topic;

@Entity
@Table(name = "quizInteraction")
public class QuizInteraction {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_quizInteraction")
    private int id_quizInteraction;
	@ManyToOne  
	@JoinColumn(name="creator_id")
	private User user;
	
	@Column(columnDefinition = "TEXT")
	@NotNull(message="Answer is required")
	private String answers;
	
	
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_question")
    private int id_question;
	
	@ManyToOne
	@JoinColumn(name="id_Quiz")
	private Quiz quiz;
	
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
        return id_quizInteraction;
    }

    public void setId(int id) {
        this.id_quizInteraction = id;
    }

    public @NotNull(message = "answer is required") String getAnswers() {
        return answers;
    }

    public void setAnswers(@NotNull(message = "answer is required") String answer) {
        this.answers = answer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }
    public int getQuestionId() {
        return id_question;
    }

    public void setQuestionId(int id) {
        this.id_question = id;
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
