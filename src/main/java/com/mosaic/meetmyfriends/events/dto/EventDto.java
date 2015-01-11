package com.mosaic.meetmyfriends.events.dto;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "event", schema = "meetfriends") 
public class EventDto {
private String eventName;
private String eventDescription;
/**
 * @return the eventName
 */
public String getEventName() {
	return eventName;
}
/**
 * @param eventName the eventName to set
 */
public void setEventName(String eventName) {
	this.eventName = eventName;
}
/**
 * @return the eventDescription
 */
public String getEventDescription() {
	return eventDescription;
}
/**
 * @param eventDescription the eventDescription to set
 */
public void setEventDescription(String eventDescription) {
	this.eventDescription = eventDescription;
}

}
