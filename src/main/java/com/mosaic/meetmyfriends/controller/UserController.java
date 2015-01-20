package com.mosaic.meetmyfriends.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mosaic.meetmyfriends.entities.Users;
import com.mosaic.meetmyfriends.model.UserModel;

@Controller
@RequestMapping()
public class UserController {
	@Autowired
	private UserModel userModel;
    @RequestMapping(method=RequestMethod.POST, value="/users/")
    public @ResponseBody ResponseEntity<String> create(@RequestBody Users user) {
    		//UserModel create = new UserModel();
    	userModel.createUser(user);
    		return new ResponseEntity<String>(userModel.message, userModel.status);
    	}
    
    @RequestMapping(method=RequestMethod.DELETE, value="/users/{username}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable("username") String uname) {
    		//UserModel delete = new UserModel();
    	userModel.deleteUser(uname);
    		return new ResponseEntity<String>(userModel.message, userModel.status);
    	}
    
    @RequestMapping(method=RequestMethod.GET, value="/users/{username}")
    public @ResponseBody ResponseEntity<String> search(@PathVariable("username") String uname) {
    		//UserModel search = new UserModel();
    		Users search_user = userModel.searchUser(uname);
    		if(search_user == null){
    			return new ResponseEntity<String>(userModel.message, userModel.status);
    		}
    		return new ResponseEntity<String>(userModel.searchUser(uname).toString(), userModel.status);
    	}
    
    @RequestMapping(method=RequestMethod.PUT, value="/users/{username}/{param}/{new_value}")
    public @ResponseBody ResponseEntity<String> modify(@PathVariable("username") String uname,
    		@PathVariable("param") String param,
    		@PathVariable("new_value") String new_value) {
    		//UserModel modify = new UserModel();
    	userModel.modifyUser(uname, param, new_value);
    		return new ResponseEntity<String>(userModel.message, userModel.status);
    	}
}