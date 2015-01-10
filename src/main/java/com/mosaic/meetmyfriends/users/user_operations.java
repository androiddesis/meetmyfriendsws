package com.mosaic.meetmyfriends.users;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction; 
import org.hibernate.Query;
 
public class user_operations {
 
	public int status=404;
	public String message="";
	
    public void create_user(String uname, String pass, String fname, String lname, String email, String country, String phone) {
        users user = new users();
        
        user.setusername(uname);
        user.setpassword(pass);
        user.setfirstname(fname);
        user.setlastname(lname);
        user.setemail(email);
        user.setcountry(country);
        user.setphone(phone);
        
        Transaction tx = null;
    	Session session = SessionFactoryUtil.getSessionFactory().openSession();
        try{
        	tx = session.beginTransaction();
        	session.save(user);
        	tx.commit();
        	message = "Success";
        	status = 200;
        }
        catch(Exception e){
        	if(tx != null){
        		tx.rollback();
        	}
        	message = e.getMessage();
        	status = 400;
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
        	status = 200;
        }
        catch(Exception e){
        	if(tx != null){
        		tx.rollback();
        	}
        	message = e.getMessage();
        	status = 400;
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
        	status = 200;
        }
        catch(Exception e){
        	if(tx != null){
        		tx.rollback();
        	}
        	message = e.getMessage();
        	status = 400;
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
        	Query query = session.createQuery("update users set email = \":new_value\" where username = \":uname\"");
        	//query.setParameter("param", param);
        	query.setParameter("new_value", new_value);
        	query.setParameter("uname", uname);
        	status = query.executeUpdate();
        	tx.commit();
        	message = "Success";
        }
        catch(Exception e){
        	if(tx != null){
        		tx.rollback();
        	}
        	message = e.getMessage();
        	status = 400;
        }
        finally{
        	session.close();
        }
        return user;
    }
    
}