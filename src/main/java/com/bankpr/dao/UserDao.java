package com.bankpr.dao;

import java.util.Optional;

import com.bankpr.model.User;

public interface UserDao { // Declaration of interface named UserDao
	
	// Method to find a user by their email (username)
	Optional<User> findByUsername(String email);
}