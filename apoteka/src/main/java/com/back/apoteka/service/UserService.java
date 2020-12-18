package com.back.apoteka.service;

import java.util.List;

import com.back.apoteka.model.User;

import com.back.apoteka.model.UserRequest;;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();
	User save(UserRequest userRequest);
	public void deleteUser(User user);
}