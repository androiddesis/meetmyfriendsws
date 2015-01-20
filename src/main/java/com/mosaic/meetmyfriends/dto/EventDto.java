package com.mosaic.meetmyfriends.dto;

import java.util.ArrayList;
import java.util.List;


public class EventDto {


		private String eventName;
		private String eventDescription;
		private Double latitude;
		private Double longitude;
		List<String> users = new ArrayList<String>();
		public String getEventName() {
			return eventName;
		}
		public void setEventName(String eventName) {
			this.eventName = eventName;
		}
		public String getEventDescription() {
			return eventDescription;
		}
		public void setEventDescription(String eventDescription) {
			this.eventDescription = eventDescription;
		}
		public Double getLatitude() {
			return latitude;
		}
		public void setLatitude(Double latitude) {
			this.latitude = latitude;
		}
		public Double getLongitude() {
			return longitude;
		}
		public void setLongitude(Double longitude) {
			this.longitude = longitude;
		}
		public List<String> getUsers() {
			return users;
		}
		public void setUsers(List<String> users) {
			this.users = users;
		}
		
}
