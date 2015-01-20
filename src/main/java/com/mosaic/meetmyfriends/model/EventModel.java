package com.mosaic.meetmyfriends.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mosaic.meetmyfriends.entities.Event;
//import com.mosaic.meetmyfriends.users.SessionFactoryUtil;

@Component
public class EventModel {
	public static String result = "None";
	
    private SessionFactory sessionFactory;

	@Autowired
	public EventModel(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public void create(Event event) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		session.save(event);
 		session.getTransaction().commit();
		result = "Success";
	}
	
	public List<Event> retrieve() {
		List<Event> events = new ArrayList<Event>();
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query queryResult = session.createQuery("from Event");
		List<?> allEvents;
		allEvents = queryResult.list();
		for (int i = 0; i < allEvents.size(); i++) {
			Event event = (Event) allEvents.get(i);
			events.add(event);
		}
		System.out.println("Database contents delivered...");
		return events;
	}

	public Event retrieveFromEventName(String eventName) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();/* lets hope an id of 1 exists! */
		String queryString = "from Event where name = :eventName";
		Query query = session.createQuery(queryString);
		query.setString("eventName", eventName);
		Object queryResult = query.uniqueResult();
		Event event = (Event) queryResult;
		session.getTransaction().commit();
		return event;
	}

	public void deleteAll() {
		Session session = sessionFactory.openSession();
		List<?> allEvents;
		session.beginTransaction();
		Query queryResult = session.createQuery("from Event");
		allEvents = queryResult.list();
		for (int i = 0; i < allEvents.size(); i++) {
			Event event = (Event) allEvents.get(i);
			System.out.println(event);
			session.delete(event);
		}
		session.getTransaction().commit();
		result = "Success";
	}

}
