package com.mosaic.meetmyfriends.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.google.gson.Gson;

@Entity
@Table(name = "event", schema = "meetfriends")
public class Event {
	@Id
	@GeneratedValue
	@Column(name = "event_id")
	private int eventId;
	 @Column(name= "name")
	private String eventName;
	 @Column(name="description")
	private String eventDescription;
	 @Column(name="latitude")
	private Double latitude;
	 @Column(name="longitude")
	private Double longitude;
	 @ManyToMany(cascade = {CascadeType.ALL})
	    @JoinTable(name="event_user", 
	                joinColumns={@JoinColumn(name="event_id")}, 
	                inverseJoinColumns={@JoinColumn(name="userid")})
	    private Set<Users> users = new HashSet<Users>();
	public Set<Users> getUsers() {
		return users;
	}

	public void setUsers(Set<Users> users) {
		this.users = users;
	}

	/**
	 * @return the eventName
	 */
	public String getEventName() {
		return eventName;
	}

	/**
	 * @param eventName
	 *            the eventName to set
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
	 * @param eventDescription
	 *            the eventDescription to set
	 */
	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	/**
	 * @return the latitude
	 */
	public Double getLatitude() {
		return latitude;
	}

	/**
	 * @param latitude
	 *            the latitude to set
	 */
	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	/**
	 * @return the longitude
	 */
	public Double getLongitude() {
		return longitude;
	}

	/**
	 * @param longitude
	 *            the longitude to set
	 */
	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	
	public Integer getId() {
		// TODO Auto-generated method stub
		return eventId;
	}
	
	@Override
	public String toString() {
    	Gson gson = new Gson();
    	String json = gson.toJson(this);
    	return json;
    }

}
