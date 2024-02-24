package com.bus.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bus.dao.UserDao;
import com.bus.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDao daoMethods;

	public boolean loginUser(String email, String password) {
		User user = daoMethods.getUserByEmail(email);
		return user != null && user.getPassword().equals(password);
	}

	public List<User> profile() {
		return daoMethods.profile();
	}
}
