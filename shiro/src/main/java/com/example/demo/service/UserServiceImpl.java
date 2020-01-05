package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.User;

public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	@Override
	public User findUserByName(String username) {
		return userDao.findByUsername(username);//自动生成SQL
		
	}

	@Override
	public void saveUser(User user) {
		userDao.save(user);//调用JPA已经实现的方法
	}

	
}
