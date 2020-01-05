package com.example.demo.service;
import org.springframework.stereotype.Service;
import com.example.demo.entity.User;

@Service
public interface UserService {
	
	User findUserByName(String username);
	
	void saveUser(User user);
	
}