package com.lemon.profiler.model;

public class Event {
	public String eventId;
	public String eventTitle;
	public String eventDetails;
	public String eventSchedule;
	public String eventStatus;
		
	public Event(String eventId, String eventTitle, String eventDetails,
			String eventSchedule, String eventStatus) {
		super();
		this.eventId = eventId;
		this.eventTitle = eventTitle;
		this.eventDetails = eventDetails;
		this.eventSchedule = eventSchedule;
		this.eventStatus = eventStatus;
	}
	
	public String getEventId() {
		return eventId;
	}
	public void setEventId(String eventId) {
		this.eventId = eventId;
	}
	public String getEventTitle() {
		return eventTitle;
	}
	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}
	public String getEventDetails() {
		return eventDetails;
	}
	public void setEventDetails(String eventDetails) {
		this.eventDetails = eventDetails;
	}
	public String getEventSchedule() {
		return eventSchedule;
	}
	public void setEventSchedule(String eventSchedule) {
		this.eventSchedule = eventSchedule;
	}
	public String getEventStatus() {
		return eventStatus;
	}
	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}
	

}
