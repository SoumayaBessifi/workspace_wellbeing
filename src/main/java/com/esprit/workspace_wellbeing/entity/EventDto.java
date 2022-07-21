package com.esprit.workspace_wellbeing.entity;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date ;

public class EventDto implements Serializable {


    private Long id_event ;  
    private String description ;
    private String title ;
    private Date start_date ;
    private Date end_date ;
    private String lieu ;
    private MultipartFile event_image;
    private String file ;
	public Long getId_event() {
		return id_event;
	}
	public void setId_event(Long id_event) {
		this.id_event = id_event;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public String getLieu() {
		return lieu;
	}
	public void setLieu(String lieu) {
		this.lieu = lieu;
	}
	public MultipartFile getEvent_image() {
		return event_image;
	}
	public void setEvent_image(MultipartFile event_image) {
		this.event_image = event_image;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	
    
}