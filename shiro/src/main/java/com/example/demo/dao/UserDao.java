package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.User;
import com.sun.xml.bind.v2.model.core.ID;

public interface UserDao extends JpaRepository<User, ID>{
	
	User findByUsername(String username);//自动生成SQL
	
}
