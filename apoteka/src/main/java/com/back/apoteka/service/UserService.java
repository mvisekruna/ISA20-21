package com.back.apoteka.service;

import java.util.List;

import com.back.apoteka.model.User;

public interface UserService {
    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll ();

}
