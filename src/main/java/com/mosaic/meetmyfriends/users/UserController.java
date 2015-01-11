package com.mosaic.meetmyfriends.users;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping()
public class UserController {
    @RequestMapping(method=RequestMethod.GET, value="/users/{username}/{password}/{firstname}/{lastname}/{email}/{country}/{phone}")
    public @ResponseBody user_operations create(@PathVariable("username") String uname,
    		@PathVariable("password") String pass,
    		@PathVariable("firstname") String fname,
    		@PathVariable("lastname") String lname,
    		@PathVariable("email") String email,
    		@PathVariable("country") String country,
    		@PathVariable("phone") String phone
    		) {
    		user_operations create = new user_operations();
    		create.create_user(uname, pass, fname, lname, email, country, phone);
    		return create;
    	}
    
    @RequestMapping(method=RequestMethod.DELETE, value="/users/{username}")
    public @ResponseBody user_operations delete(@PathVariable("username") String uname) {
    		user_operations delete = new user_operations();
    		delete.delete_user(uname);
    		return delete;
    	}
    
    @RequestMapping(method=RequestMethod.GET, value="/users/{username}")
    public @ResponseBody users search(@PathVariable("username") String uname) {
    		user_operations search = new user_operations();
    		return search.search_user(uname);
    	}
    
    @RequestMapping(method=RequestMethod.POST, value="/users/{username}/{param}/{new_value}")
    public @ResponseBody user_operations modify(@PathVariable("username") String uname,
    		@PathVariable("param") String param,
    		@PathVariable("new_value") String new_value) {
    		user_operations modify = new user_operations();
    		modify.modify_user(uname, param, new_value);
    		return modify;
    	}
}