package com.gitamcollege;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gitamcollege.entity.User;

public interface UserRepo extends JpaRepository<User, Long>{

}
