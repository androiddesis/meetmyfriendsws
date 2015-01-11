package com.mosaic.meetmyfriends.events.model;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.mosaic.meetmyfriends.events.dto.EventDto;

public class EventModel {
	public static String result = "None";
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;


	public static void create(EventDto event) {
		SessionFactory factory = createSessionFactory(EventDto.class);
		Session session = factory.getCurrentSession();
		session.beginTransaction();

		session.save(event);
		session.getTransaction().commit();
		result = "Success";
	}

	public static List<EventDto> retrieve() {
		List<EventDto> events = new ArrayList<EventDto>();
		SessionFactory factory = createSessionFactory(EventDto.class);
		Session session = factory.getCurrentSession();
		session.beginTransaction();
		Query queryResult = session.createQuery("from EventDto");
		List<?> allEvents;
		allEvents = queryResult.list();
		for (int i = 0; i < allEvents.size(); i++) {
			EventDto event = (EventDto) allEvents.get(i);
			events.add(event);
		}
		System.out.println("Database contents delivered...");
		return events;
	}

	public static EventDto retrieveFromEventName(String eventName) {
		SessionFactory factory = createSessionFactory(EventDto.class);
		Session session = factory.getCurrentSession();
		session.beginTransaction();/* lets hope an id of 1 exists! */
		String queryString = "from EventDto where name = :eventName";
		Query query = session.createQuery(queryString);
		query.setString("eventName", eventName);
		Object queryResult = query.uniqueResult();
		EventDto event = (EventDto) queryResult;
		session.getTransaction().commit();
		return event;
	}

	public static void deleteAll() {
		SessionFactory factory = createSessionFactory(EventDto.class);
		Session session = factory.getCurrentSession();
		List<?> allEvents;
		session.beginTransaction();
		Query queryResult = session.createQuery("from EventDto");
		allEvents = queryResult.list();
		for (int i = 0; i < allEvents.size(); i++) {
			EventDto event = (EventDto) allEvents.get(i);
			System.out.println(event);
			session.delete(event);
		}
		session.getTransaction().commit();
		result = "Success";
	}

	public static SessionFactory createSessionFactory(Class<EventDto> classObj) {
	    Configuration configuration = new Configuration();
	    configuration.addAnnotatedClass(classObj);
	    configuration.configure();
	    serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	            configuration.getProperties()).build();
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}
}
