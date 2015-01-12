package com.mosaic.meetmyfriends.users;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.mosaic.meetmyfriends.users.users;

@Controller
@RequestMapping()
public class UserController {
    @RequestMapping(method=RequestMethod.POST, value="/users/")
    public @ResponseBody ResponseEntity<String> create(@RequestBody users user) {
    		user_operations create = new user_operations();
    		create.create_user(user);
    		return new ResponseEntity<String>(create.message, create.status);
    	}
    
    @RequestMapping(method=RequestMethod.DELETE, value="/users/{username}")
    public @ResponseBody ResponseEntity<String> delete(@PathVariable("username") String uname) {
    		user_operations delete = new user_operations();
    		delete.delete_user(uname);
    		return new ResponseEntity<String>(delete.message, delete.status);
    	}
    
    @RequestMapping(method=RequestMethod.GET, value="/users/{username}")
    public @ResponseBody ResponseEntity<String> search(@PathVariable("username") String uname) {
    		user_operations search = new user_operations();
    		users search_user = search.search_user(uname);
    		if(search_user == null){
    			return new ResponseEntity<String>(search.message, search.status);
    		}
    		return new ResponseEntity<String>(search.search_user(uname).toString(), search.status);
    	}
    
    @RequestMapping(method=RequestMethod.PUT, value="/users/{username}/{param}/{new_value}")
    public @ResponseBody ResponseEntity<String> modify(@PathVariable("username") String uname,
    		@PathVariable("param") String param,
    		@PathVariable("new_value") String new_value) {
    		user_operations modify = new user_operations();
    		modify.modify_user(uname, param, new_value);
    		return new ResponseEntity<String>(modify.message, modify.status);
    	}
}