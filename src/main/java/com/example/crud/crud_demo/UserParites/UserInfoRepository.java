package com.example.crud.crud_demo.UserParites;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;



public interface UserInfoRepository extends JpaRepository<User, Integer> {
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
    public User findByName(@Param("username") String username);

}