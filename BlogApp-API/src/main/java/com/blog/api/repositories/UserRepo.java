package com.blog.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blog.api.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

	User findByname(String username);

}
