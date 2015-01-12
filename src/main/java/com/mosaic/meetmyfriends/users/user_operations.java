package com.mosaic.meetmyfriends.users;

import org.springframework.http.HttpStatus;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction; 
import org.hibernate.Query;
 
public class user_operations {
	public HttpStatus status = HttpStatus.I_AM_A_TEAPOT;
	public String message="";
	
    public void create_user(users user) { 
        Transaction tx = null;
    	SessionFactory sessionfactory = SessionFactoryUtil.getSessionFactory();
    	if(sessionfactory == null){
    		message = "Connection Error";
    		status = HttpStatus.NOT_FOUND;
    		return;
    	}
    			
    	Session session = sessionfactory.openSession();
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

    
    public void delete_user(String uname) {
        Transaction tx = null;
    	Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try{
        	tx = session.beginTransaction();
        	users user = new users();
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
    
    public users search_user(String uname){
    	Transaction tx = null;
    	Session session = SessionFactoryUtil.getSessionFactory().openSession();
    	users user = null;
        try{
        	tx = session.beginTransaction();
        	user = (users) session.createQuery("from users where username = :uname")
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
    
    public users modify_user(String uname, String param, String new_value){
    	Transaction tx = null;
    	Session session = SessionFactoryUtil.getSessionFactory().openSession();
    	users user = null;
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