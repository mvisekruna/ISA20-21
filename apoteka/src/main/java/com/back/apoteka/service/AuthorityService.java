package com.back.apoteka.service;

import java.util.List;

import com.back.apoteka.model.Authority;;

public interface AuthorityService {
	List<Authority> findById(Long id);
	List<Authority> findByname(String name);
}
