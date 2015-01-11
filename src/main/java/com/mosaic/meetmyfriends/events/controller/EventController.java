package com.mosaic.meetmyfriends.events.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mosaic.meetmyfriends.events.dto.EventDto;
import com.mosaic.meetmyfriends.events.model.EventModel;

@Controller

public class EventController {
	@RequestMapping(value="/event/",method = RequestMethod.POST)
	public void createEvents(@RequestBody EventDto event) {
		EventModel.create(event);
	}
	@RequestMapping(method = RequestMethod.GET,value="/event/{eventName}")
	public @ResponseBody EventDto getEventFromEventName(@PathVariable("eventName") String eventName){
		return EventModel.retrieveFromEventName(eventName);
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/event/")
	public @ResponseBody ArrayList<EventDto> getAllEvents(){
		return (ArrayList<EventDto>) EventModel.retrieve();
	}
}
