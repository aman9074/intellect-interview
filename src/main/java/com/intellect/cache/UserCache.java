package com.intellect.cache;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.intellect.model.User;

public class UserCache {

	public static List<User> users;
	
	//initial cache
	static {
		users = new ArrayList<>();
		
		users.add(new User(UUID.randomUUID().toString(), "Aman", "Kumar", "xyz@gmail.com", 560030, new Date(), true));
		users.add(new User(UUID.randomUUID().toString(), "Vijit", "Sharma", "vij@gmail.com", 560030, new Date(), true));
	}
}
