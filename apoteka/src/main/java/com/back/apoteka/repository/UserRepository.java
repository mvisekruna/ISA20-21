package com.back.apoteka.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.back.apoteka.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername( String username );
}
