package com.user.service.services;

import java.util.List;

import com.user.service.entities.User;

public interface UserService {
	
	//create user
	
	User saveUser(User user);
	
	//get all user
	List<User> getAllUsers();
	
	//get single user of given id
	
	User getUser(String userId);
	
	//todo for update delete

}
