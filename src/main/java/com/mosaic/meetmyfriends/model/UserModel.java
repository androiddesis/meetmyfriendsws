package com.mosaic.meetmyfriends.model;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.mosaic.meetmyfriends.entities.Users;
//import com.mosaic.meetmyfriends.users.SessionFactoryUtil;
 
@Component
public class UserModel {
	public HttpStatus status = HttpStatus.I_AM_A_TEAPOT;
	public String message="";

    private static SessionFactory sessionFactory;
	
	@Autowired
	public UserModel(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    public void createUser(Users user) { 
        Transaction tx = null;
    	//SessionFactory sessionfactory = SessionFactoryUtil.getSessionFactory();
    	if(sessionFactory == null){
    		message = "Connection Error";
    		status = HttpStatus.NOT_FOUND;
    		return;
    	}
    			
    	Session session = sessionFactory.openSession();
        try{
        	tx = session.beginTransaction();
        	session.save(user);
        	tx.commit();
        	message = "Success";
        	status = HttpStatus.OK;
        }
        catch(Exception e){
        	message = e.getCause().getMessage();
        	status = HttpStatus.BAD_REQUEST;	
        	if(tx != null){
         		tx.rollback();
         	}  
        }
        finally{
        	session.close();
        }
    }

    
    public void deleteUser(String uname) {
        Transaction tx = null;
    	Session session = sessionFactory.openSession();
        try{
        	tx = session.beginTransaction();
        	Users user = new Users();
        	user.setusername(uname);
        	session.delete(user);
        	tx.commit();
        	message = "Success";
        	status = HttpStatus.OK;
        }
        catch(Exception e){
        	if(tx != null){
        		tx.rollback();
        	}
        	message = e.getMessage();
        	status = HttpStatus.BAD_REQUEST;
        }
        finally{
        	session.close();
        }
    }
    
    public Users searchUser(String uname){
    	Transaction tx = null;
    	Session session = sessionFactory.openSession();
    	Users user = null;
        try{
        	tx = session.beginTransaction();
        	user = (Users) session.createQuery("from Users where username = :uname")
                    .setParameter("uname", uname).list().get(0);
        	tx.commit();
        	message = "Success";
        	status = HttpStatus.OK;
        }
        catch(Exception e){
        	if(tx != null){
        		tx.rollback();
        	}
        	message = e.getCause().getMessage();
        	status = HttpStatus.BAD_REQUEST;
        }
        finally{
        	session.close();
        }
        return user;
    }
    
    public Users modifyUser(String uname, String param, String new_value){
    	Transaction tx = null;
    	Session session = sessionFactory.openSession();
    	Users user = null;
        try{
        	tx = session.beginTransaction();
        	//TO-DO: figure out why setParameter does not work
        	Query query = session.createQuery("update users set "+param+"='"+new_value+"' where username='"+uname+"'");
        	//query.setParameter("param", param);
        	//query.setParameter("new_value", new_value);
        	//query.setParameter("uname", uname);
        	query.executeUpdate();
        	status = HttpStatus.OK;
        	tx.commit();
        	message = "Success";
        }
        catch(Exception e){
        	if(tx != null){
        		tx.rollback();
        	}
        	message = e.getCause().getMessage();
        	status = HttpStatus.BAD_REQUEST;
        }
        finally{
        	session.close();
        }
        return user;
    }
    
}