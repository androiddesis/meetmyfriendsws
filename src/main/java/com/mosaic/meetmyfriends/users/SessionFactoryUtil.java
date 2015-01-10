package com.mosaic.meetmyfriends.users;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
 
 
public class SessionFactoryUtil {
 
    private static SessionFactory sessionFactory=null;
     
    public static SessionFactory getSessionFactory() {
        try {
        	Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            sessionFactory = configuration.buildSessionFactory(ssrb.build());
        } 
        catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
        
        return sessionFactory;
    }
 
}
