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
import com.back.apoteka.request.ChangePassRequest;
import com.back.apoteka.request.UserUpdateRequest;
import com.back.apoteka.security.auth.JwtAuthenticationRequest;
import com.back.apoteka.service.UserService;
import com.back.apoteka.service.impl.CustomUserDetailsService;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private CustomUserDetailsService customUserService;
	// Za pristup ovoj metodi neophodno je da ulogovani korisnik ima ADMIN ulogu
	// Ukoliko nema, server ce vratiti gresku 403 Forbidden
	// Korisnik jeste autentifikovan, ali nije autorizovan da pristupi resursu
	@GetMapping("/user/{userId}")
	@PreAuthorize("hasRole('ADMIN')")
	public User loadById(@PathVariable int userId) {
		return this.userService.findById(Integer.toUnsignedLong(userId));
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
	
	@PostMapping("/savepatient")
	@PreAuthorize("hasAnyRole(\"PATIENT\",\"SYSTEM_ADMIN\")")
	public User savePatient(@RequestBody UserRequest userRequest) {
		return this.userService.save(userRequest);
	}
	
	@PostMapping("/updatepatient")
	@PreAuthorize("hasRole('USER')")//zasad mek stoji user dok ne bude trebalo patient
	public User updatePatient(@RequestBody UserUpdateRequest userRequest) {
		System.out.println("usao u updatepatient");
		return this.userService.update(userRequest);
	}
	@PostMapping("/pass")
	@PreAuthorize("hasAnyRole(\"PATIENT\",\"USER\")")//zasad mek stoji user dok ne bude trebalo patient
	public User changePassword(@RequestBody ChangePassRequest cpr) {
		return this.customUserService.changePassword(cpr.getOldPassword(), cpr.getNewPassword());
	}
	
	
	@GetMapping("/whoami")
	//@PreAuthorize("hasRole('USER')")
	public User user(@RequestBody JwtAuthenticationRequest body) {
		System.out.println(this.userService.findByEmail(body.getEmail()));
		return this.userService.findByEmail(body.getEmail());
	}
	
	@PostMapping("/{email}")
	public ResponseEntity<User> findByEmail(@RequestBody String e, @Context HttpServletRequest request) {
		User user = userService.findByEmail(e);
		System.out.println(e);
		System.out.println(user);
		 HttpSession session = request.getSession();
         System.out.println("\nSesija KorisnikData: " + session);
         System.out.println(new ResponseEntity<User>(user, HttpStatus.OK));
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
