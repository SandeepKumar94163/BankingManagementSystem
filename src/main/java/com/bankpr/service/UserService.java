package com.bankpr.service;

import java.util.Optional;

import com.bankpr.model.User;

public interface UserService {

	public Optional<User> login(String email, String password);
}
