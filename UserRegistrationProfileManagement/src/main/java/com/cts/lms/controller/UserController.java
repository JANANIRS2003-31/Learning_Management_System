package com.cts.lms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.lms.exceptions.UserNotFound;
import com.cts.lms.model.User;
import com.cts.lms.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService service;

	@PostMapping("/create")
	public String saveUser(@RequestBody @Validated User user) {
			return service.saveUser(user);
	}
	@PutMapping("/update")
	public User updateUser(@RequestBody @Validated User user) {
		return service.updateUser(user);
	}

	@GetMapping("/getbyId/{email}")
	public User getUserByEmail(@PathVariable("email") String email) throws UserNotFound {
		return service.getUserByEmail(email);
	}

	@GetMapping("/getall")
	public List<User> getAllUser() {
		return service.getAllUser();
	}

	@DeleteMapping("/delete/{did}")
	public String deleteUser(@PathVariable("did") int userId) {
		return service.deleteUser(userId);
	}

}
