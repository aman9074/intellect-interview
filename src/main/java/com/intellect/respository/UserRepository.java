package com.intellect.respository;

import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.intellect.cache.UserCache;
import com.intellect.model.User;

@Repository
public class UserRepository {

	public void createUser(final User user) {
		UserCache.users.add(user);
	}
	
	public void deleteUser(final String userId) {
		User userToBeDeleted = UserCache.users.stream().filter(user -> user.getId().equals(userId)).collect(Collectors.toList()).get(0);
		userToBeDeleted.setActive(false);
	}
}
