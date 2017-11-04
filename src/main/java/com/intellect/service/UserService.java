package com.intellect.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.intellect.cache.UserCache;
import com.intellect.message.APIResponseDTO;
import com.intellect.message.UserDTO;
import com.intellect.message.ValidationErrorDTO;
import com.intellect.model.User;
import com.intellect.respository.UserRepository;
import com.intellect.utils.DateUtility;

@Service
public class UserService {

	@Autowired private UserRepository userRepository;
	
	public APIResponseDTO createUser(final UserDTO userDTO) {
		APIResponseDTO response = new APIResponseDTO();
		
		if(userDTO.getEmail()==null || userDTO.getEmail().trim().length()==0) {
			response.setUserId(userDTO.getId());
			response.setResMsg("Input validation error");
			ValidationErrorDTO errorDTO = new ValidationErrorDTO();
			errorDTO.setField("email");
			errorDTO.setMessage("Email is mandatory");
			response.addValErrors(errorDTO);
		}
		
		if(checkUserExistByEmailAddress(userDTO.getEmail())) {
			response.setUserId(userDTO.getId());
			response.setResMsg("Email address already exists");
			return response;
		}
		
		User user = new User();
		if(StringUtils.isEmpty(user.getId())) {
			user.setId(UUID.randomUUID().toString());
		} else {
			user.setId(userDTO.getId());
		}
		user.setfName(userDTO.getfName());
		user.setlName(userDTO.getlName());
		user.setEmail(userDTO.getEmail());
		user.setPincode(userDTO.getPincode());
		user.setActive(true);
		try {
			user.setBirthDate(DateUtility.convertStringToDate(userDTO.getBirthDate()));
		} catch (ParseException e) {
			response.setResMsg("Input validation error");
			ValidationErrorDTO errorDTO = new ValidationErrorDTO();
			errorDTO.setField("birthDate");
			errorDTO.setMessage("Date of Birth format is invalid");
			response.addValErrors(errorDTO);
		}
		
		if(response.getValErrors()!=null && response.getValErrors().size()>0) {
			return response;
		}
		
		userRepository.createUser(user);
		response.setUserId(user.getId());
		response.setResMsg("User created successfully");
		return response;
	}
	
	private boolean checkUserExistByEmailAddress(final String emailAddress) {
		return UserCache.users.stream().anyMatch(user -> user.getEmail().equals(emailAddress));
	}
	
	private boolean checkUserExistById(final String userId) {
		return UserCache.users.stream().anyMatch(user -> user.getId().equals(userId));
	}
	
	public APIResponseDTO updateUser(final String userId, final UserDTO userDTO) {
		APIResponseDTO response = new APIResponseDTO();
		
		response.setUserId(userId);
		if(checkUserExistById(userId)) {
			List<User> filteredUser = UserCache.users.stream().filter(user -> user.getEmail().equals(userDTO.getEmail())).collect(Collectors.toList());
			User user = filteredUser.get(0);
			
			user.setPincode(userDTO.getPincode());
			
			try {
				user.setBirthDate(DateUtility.convertStringToDate(userDTO.getBirthDate()));
			} catch (ParseException e) {
				response.setResMsg("Input validation error");
				ValidationErrorDTO errorDTO = new ValidationErrorDTO();
				errorDTO.setField("birthDate");
				errorDTO.setMessage("Date of Birth format is invalid");
				response.addValErrors(errorDTO);
			}
		}
		
		if(response.getValErrors()!=null && response.getValErrors().size()>0) {
			return response;
		}
		
		response.setResMsg("User updated successfully");
		return response;
	}
	
	public APIResponseDTO deleteUser(final String userId) {
		APIResponseDTO response = new APIResponseDTO();
		
		response.setUserId(userId);
		if(checkUserExistById(userId)) {
			userRepository.deleteUser(userId);
			response.setResMsg("User deleted successfully");
		} else {
			response.setResMsg("User does not exist");
		}
		
		return response;
	}
	
	public List<UserDTO> getAllUsers() {
		List<UserDTO> users = new ArrayList<>();
		
		for (User user : UserCache.users) {
			try {
				users.add(new UserDTO(user.getId(), user.getfName(), user.getlName(), user.getEmail(), 
						user.getPincode(), DateUtility.convertDateToString(user.getBirthDate()), user.isActive()));
			} catch (ParseException e) { }
		}
		return users;
	}
}
