package com.back.apoteka.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.back.apoteka.model.Authority;
import com.back.apoteka.model.User;
import com.back.apoteka.repository.UserRepository;
import com.back.apoteka.request.UserRequest;
import com.back.apoteka.request.UserUpdateRequest;
import com.back.apoteka.service.AuthorityService;
import com.back.apoteka.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthorityService authService;

	@Autowired
	private CustomUserDetailsService customUserService;

	public User findById(Long id) throws AccessDeniedException {
		User u = userRepository.findById(id).orElseGet(null);
		return u;
	}

	public List<User> findAll() throws AccessDeniedException {
		List<User> result = userRepository.findAll();
		return result;
	}

	@Override
	public User save(UserRequest userRequest) {
		User u = new User();
		u.setEmail(userRequest.getEmail());
		u.setPassword(passwordEncoder.encode(userRequest.getPassword()));
		u.setFirstName(userRequest.getFirstname());
		u.setLastName(userRequest.getLastname());
		u.setEnabled(true);
		u.setCity(userRequest.getCity());
		u.setState(userRequest.getState());
		u.setPhoneNumber(userRequest.getPhone());
		u.setHomeAddress(userRequest.getAddress());
		Authority authority = authService.findName("ROLE_PATIENT");
		u.setAuthority(authority);
		List<Authority> auth = authService.findByname("ROLE_PATIENT");
		u.setAuthorities(auth);
		
		
		u = this.userRepository.save(u);
		return u;
	}

	@Override
	public User update(UserUpdateRequest uur) {
		Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
		String email = currentUser.getName();
		User u = (User) customUserService.loadUserByUsername(email);
		u.setFirstName(uur.getFirstname());
		u.setLastName(uur.getLastname());
		u.setCity(uur.getCity());
		u.setState(uur.getState());
		u.setPhoneNumber(uur.getPhone());
		u.setHomeAddress(uur.getAddress());
		return userRepository.save(u);
	}
	
	
	@Transactional(rollbackFor = Exception.class)
	public void deleteUser(User user) {
		userRepository.delete(user);
		
	}

	@Override
	public User findByEmail(String email) {
		System.out.println(email);
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> findAllDermatoligists(){
		List<User> users=findAll();
		List<User> dermatologists=findAll();
		dermatologists.removeAll(dermatologists);
		for (User u: users) {
			List<Authority> lista=(List<Authority>) u.getAuthorities();
			System.out.println(lista.get(0).getName());
			if (lista.get(0).getName().contains("ROLE_DERMATOLOGIST")) {
				dermatologists.add(u);
			}
		}
		return dermatologists;
	}


}
