package com.mosaic.meetmyfriends.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mosaic.meetmyfriends.dto.EventDto;
import com.mosaic.meetmyfriends.entities.Event;
import com.mosaic.meetmyfriends.entities.Users;
import com.mosaic.meetmyfriends.model.EventModel;
import com.mosaic.meetmyfriends.model.UserModel;

@Controller
public class EventController {
	
	@Autowired
	private EventModel eventModel;
	@Autowired
	private UserModel userModel;
	
	@RequestMapping(value="/event/",method = RequestMethod.POST, consumes={"application/json"})
	public void createEvents(@RequestBody EventDto eventDto) {
		Event event = new Event();
		event.setEventDescription(eventDto.getEventDescription());
		event.setEventName(eventDto.getEventName());
		event.setLatitude(eventDto.getLatitude());
		event.setLongitude(eventDto.getLongitude());
		ArrayList<String> usersAssociatedToEvent = (ArrayList<String>) eventDto.getUsers();
		for (String user : usersAssociatedToEvent) {
			//UserModel search = new UserModel();
    		Users search_user = userModel.searchUser(user);
    		event.getUsers().add(search_user);
		}
		
		eventModel.create(event);
	}
	@RequestMapping(method = RequestMethod.GET,value="/event/{eventName}", produces={"application/json"})
	public @ResponseBody Event getEventFromEventName(@PathVariable("eventName") String eventName){
		return eventModel.retrieveFromEventName(eventName);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/event/", produces={"application/json"})
	
	public @ResponseBody ArrayList<Event> getAllEvents(){
		return (ArrayList<Event>) eventModel.retrieve();
	}
}
