package com.intellect.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.intellect.message.APIResponseDTO;
import com.intellect.message.UserDTO;
import com.intellect.service.UserService;

@RestController
public class UserController {

	@Autowired private UserService userService;
	
	@PostMapping(path="user")
	public APIResponseDTO createUser(@RequestBody final UserDTO userDTO) {
		return userService.createUser(userDTO);
	}
	
	@PutMapping(path="user/{userId}")
	public APIResponseDTO updateUser(@PathVariable("userId") final String userId,
			@RequestBody final UserDTO userDTO) {
		return userService.updateUser(userId, userDTO);
	}
	
	@DeleteMapping(path="user/{userId}")
	public APIResponseDTO deleteUser(@PathVariable("userId") final String userId) {
		return userService.deleteUser(userId);
	}
	
	@GetMapping(path="users")
	public List<UserDTO> getAllUsers() {
		return userService.getAllUsers();
	}
}
