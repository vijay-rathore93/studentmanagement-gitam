package com.gitamcollege;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gitamcollege.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {

	Optional<User> findByToken(String token);

	boolean existsByEmail(String email);

	Optional<User> findByEmail(String email);

}
