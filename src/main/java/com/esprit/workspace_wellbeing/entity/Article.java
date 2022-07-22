package com.esprit.workspace_wellbeing.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Article implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id_article;

	@NotNull
	@Column(unique=true)
	@Size(max = 100)
	private String title;

	@NotBlank
	@Column(length = 10000)
	private String description;

	@CreationTimestamp
	private Timestamp creationDateTimeStamp;

	@ManyToOne
	@JoinColumn(name = "userWriterId")
	User userWriterId;

	public Long getId_article() {
		return id_article;
	}

	public void setId_article(Long id_article) {
		this.id_article = id_article;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Timestamp getCreationDateTimeStamp() {
		return creationDateTimeStamp;
	}

	public void setCreationDateTimeStamp(Timestamp creationDateTimeStamp) {
		this.creationDateTimeStamp = creationDateTimeStamp;
	}

	public User getUserWriterId() {
		return userWriterId;
	}

	public void setUserWriterId(User userWriterId) {
		this.userWriterId = userWriterId;
	}

	/**
	 * @param title
	 * @param description
	 * @param creationDateTimeStamp
	 * @param userWriterId
	 */
	public Article(@NotNull @Size(max = 100) String title, @NotBlank String description,
			Timestamp creationDateTimeStamp, User userWriterId) {
		super();
		this.title = title;
		this.description = description;
		this.creationDateTimeStamp = creationDateTimeStamp;
		this.userWriterId = userWriterId;
	}

	/**
	 * @param id_article
	 * @param title
	 * @param description
	 * @param creationDateTimeStamp
	 * @param userWriterId
	 */
	public Article(Long id_article, @NotNull @Size(max = 100) String title, @NotBlank String description,
			Timestamp creationDateTimeStamp, User userWriterId) {
		super();
		this.id_article = id_article;
		this.title = title;
		this.description = description;
		this.creationDateTimeStamp = creationDateTimeStamp;
		this.userWriterId = userWriterId;
	}

}
