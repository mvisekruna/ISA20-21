package com.back.apoteka.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.back.apoteka.model.User;
import com.back.apoteka.model.UserRequest;
import com.back.apoteka.security.auth.JwtAuthenticationRequest;
import com.back.apoteka.service.UserService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;

	// Za pristup ovoj metodi neophodno je da ulogovani korisnik ima ADMIN ulogu
	// Ukoliko nema, server ce vratiti gresku 403 Forbidden
	// Korisnik jeste autentifikovan, ali nije autorizovan da pristupi resursu
	@GetMapping("/user/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public User loadById(@PathVariable Long userId) {
		return this.userService.findById(userId);
	}

	@GetMapping("/user/all")
	@PreAuthorize("hasRole('ADMIN')")
	public List<User> loadAll() {
		return this.userService.findAll();
	}

	/*@GetMapping("/whoami")
	@PreAuthorize("hasRole('USER')")
	public User user(Principal user) {
		return this.userService.findByUsername(user.getName());
	}*/
	
	@PostMapping("saveuser")
	@PreAuthorize("hasAnyRole(\"PATIENT\",\"SYSTEM_ADMIN\")")
	public User save(UserRequest userRequest) {
		return this.userService.save(userRequest);
	}
	
	@GetMapping("/whoami")
	//@PreAuthorize("hasRole('USER')")
	public User user(JwtAuthenticationRequest body) {
		return this.userService.findByEmail(body.getEmail());
	}
	
	@PostMapping("/{email}")
	public ResponseEntity<User> findByEmail(@RequestBody String e, @Context HttpServletRequest request) {
		User user = userService.findByEmail(e);
		System.out.println(e);
		 HttpSession session = request.getSession();
         System.out.println("\nSesija KorisnikData: " + session);
         return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@DeleteMapping("user/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<User> deleteOne(@PathVariable(value="id") Long userId){
		User u = userService.findById(userId);
		if (u==null) {
			return ResponseEntity.notFound().build();
		}
		userService.deleteUser(u);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/foo")
    public Map<String, String> getFoo() {
        Map<String, String> fooObj = new HashMap<>();
        fooObj.put("foo", "bar");
        return fooObj;
    }
}
