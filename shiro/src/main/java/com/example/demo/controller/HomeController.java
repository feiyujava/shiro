package com.example.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.PasswordHelper;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

@RestController
@RequestMapping
public class HomeController {
	
	@Autowired
	private UserService UserService;
	@Autowired
	private PasswordHelper PasswordHelper;
	
	@GetMapping("/login")
	public Object login() {
		return "this is login page";
	}
	
	@GetMapping("/unauthc")
	public Object unauthc() {
		return "this is unauthc page";
	}
	
	
	@GetMapping("/doLogin")
	public Object doLogin(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		
		UsernamePasswordToken token=new UsernamePasswordToken();
		Subject subject=SecurityUtils.getSubject();
		
		try {
			subject.login(token);
		} catch (IncorrectCredentialsException  ice) {
			return "password error";
		}catch(UnknownAccountException uae) {
			return "usernae error";
		}
		
		User user=UserService.findUserByName(username);
		subject.getSession().setAttribute("user", user);
		
		return "success";
	}
	
	public Object register(@RequestParam("username") String username,
			@RequestParam("password")String password) {
		
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		PasswordHelper.encryptPassword(user);
		
		UserService.saveUser(user);
		return "success";
	}
}


















