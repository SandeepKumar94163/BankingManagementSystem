package com.bankpr.serviceImpl;

import java.util.Optional;

import com.bankpr.daoImpl.UserDaoImpl;
import com.bankpr.model.User;
import com.bankpr.service.UserService;

public class UserServiceImpl implements UserService {

	UserDaoImpl udao = new UserDaoImpl();

	public UserServiceImpl(UserDaoImpl udao) {
		this.udao = udao;
	}

	@Override
	public Optional<User> login(String email, String password) {

		Optional<User> userOptional = udao.findByUsername(email);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			if (user.getPassword().equals(password)) {
				return Optional.of(user);
			}
		}
		return Optional.empty();
	}

}
