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
        catch (Exception ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            return null;
        }
        return sessionFactory;
    }
 
}
