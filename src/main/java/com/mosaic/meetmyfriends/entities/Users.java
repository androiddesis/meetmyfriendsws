package com.mosaic.meetmyfriends.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.google.gson.Gson;
@Entity
@Table(name = "user", schema = "meetfriends")
public class Users {
	@Id
	@GeneratedValue
	@Column(name="userid")
	private int userId;
	 @Column(name="username")
	private String username="";
	 @Column(name="password")
    private String password="";
	 @Column(name="firstname")
    private String firstname="";
	 @Column(name="lastname")
    private String lastname="";
	 @Column(name="email")
    private String email="";
	 @Column(name="country")
    private String country="";
	 @Column(name="phone")
    private String phone="";
//    @ManyToMany(mappedBy="users")
//    private Set<EventDto> events = new HashSet<EventDto>();
//    
//    public Set<EventDto> getEvents() {
//		return events;
//	}
//
//	public void setEvents(Set<EventDto> events) {
//	this.events = events;
//	}

	public Users() {
    	//do nothing
    }

    public void setphone(String phone) {
        this.phone = phone;
    }
    
    public void setusername(String username) {
        this.username = username;
    }

    public void setpassword(String password) {
        this.password = password;
    }
    
    public void setfirstname(String firstname) {
        this.firstname = firstname;
    }
    
    public void setlastname(String lastname) {
        this.lastname = lastname;
    }
    
    public void setemail(String email) {
        this.email = email;
    }
    
    public void setcountry(String country) {
        this.country = country;
    }
    
    public String getusername() {
        return username;
    }

    public String getpassword() {
        return password;
    }
    
    public String getfirstname(){
    	return firstname;
    }
    
    public String getlastname(){
    	return lastname;
    }
    
    public String getemail(){
    	return email;
    }
    
    public String getcountry(){
    	return country;
    }
    
    public String getphone() {
        return phone;
    }
    
    @Override
	public String toString() {
    	Gson gson = new Gson();
    	String json = gson.toJson(this);
    	return json;
    }

}
