package com.macrosoftas.archijee.service;

import com.macrosoftas.archijee.model.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}
