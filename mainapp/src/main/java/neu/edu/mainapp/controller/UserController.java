package neu.edu.mainapp.controller;

import java.util.ArrayList;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;


import neu.edu.mainapp.dto.UserDTO;
import neu.edu.mainapp.entity.User;
import neu.edu.mainapp.service.UserService;
import neu.edu.mainapp.resources.SendEmail;

@RestController
@RequestMapping("/main")
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value= "/login", method= RequestMethod.POST)
	public ResponseEntity<User> login(@RequestBody Map<String, Object> payload) {
		String username = (String) payload.get("username");
		String password = (String) payload.get("password");
		User user = userService.getUserByUsername(username);
		ResponseEntity res;
		if(username.equals(user.getUsername())  && password.equals(user.getPassword())) {
			res = new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			res = new ResponseEntity<>(user, HttpStatus.NOT_FOUND);
		}
		return res;
	}
	
	@RequestMapping(value= "/getUsers", method= RequestMethod.GET)
	public ResponseEntity<ArrayList<User>> getAllUsers() {
		ArrayList<User> users = userService.getAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	
	@RequestMapping(value= "/getUsersByRole/{role}", method= RequestMethod.GET)
	public ResponseEntity<ArrayList<User>> getAllUsersByRole(@PathVariable String role) {
		ArrayList<User> users = userService.getAllUsersByRole(role);
		return new ResponseEntity<>(users, HttpStatus.OK);

	}

	
	@RequestMapping(value= "/getUserByUsername/{username}", method= RequestMethod.GET)
	public ResponseEntity<User> getAllUsersByUsername(@PathVariable String username) {
		User user = userService.getUserByUsername(username);
		return new ResponseEntity<>(user, HttpStatus.OK);

	}
		
	@RequestMapping(value= "/addUser", method= RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) {
		User users = userService.insertUser(userDTO);
		SendEmail sendEmail = new SendEmail();
		sendEmail.sendEmailFunction(userDTO.getEmail(), "Registration Successfull", "Thanks for registering with us. Your one stop solution to all your skincare doubts. Happy shopping!!! Login to explore!");
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/deleteUser/{username}", method= RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteUser(@PathVariable String username){
		boolean isDeleted = userService.deleteUser(username);
		return new ResponseEntity<>(isDeleted, HttpStatus.OK);
	}
	
	@RequestMapping(value= "/updateUser", method= RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@RequestBody UserDTO userModel){
		User user = userService.updateUser(userModel);
		return new ResponseEntity<>(user, HttpStatus.OK);
	}
}
