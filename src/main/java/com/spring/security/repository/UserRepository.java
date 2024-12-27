package com.spring.security.repository;

import com.spring.security.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<AppUser, Long> {

       AppUser findByUsername(String username);
}
