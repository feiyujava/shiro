package com.example.demo;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Permission;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

public class EnceladusShiroRealm extends AuthorizingRealm{
	
	@Autowired
	private UserService userService;
	
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		String username=(String) principals.getPrimaryPrincipal();
		User user=userService.findUserByName(username);
		for(Role role:user.getRoles()) {
			authorizationInfo.addRole(role.getRole());
			for(Permission permission:role.getPermissions()) {
				authorizationInfo.addStringPermission(permission.getName());
			}
		}
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username=(String) token.getPrincipal();
		User user=userService.findUserByName(username);
		if(user==null) {
			return null;
		}
		SimpleAuthenticationInfo authenticationInfo
			=new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(),
						ByteSource.Util.bytes(user.getSalt()), getName());
		return authenticationInfo;
	}

}
