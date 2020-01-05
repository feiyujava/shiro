package com.example.demo;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.context.annotation.Bean;

public class ShiroConfig {

	@Bean
	public PasswordHelper passwordHelper() {
		return new PasswordHelper();
	}
	
	@Bean
	public HashedCredentialsMatcher hashedCredentialsMatcher() {
		HashedCredentialsMatcher hashedCredentialsMatcher=new HashedCredentialsMatcher();
		hashedCredentialsMatcher.setHashAlgorithmName(PasswordHelper.ALGORITHM_NAME);//基础散列
		hashedCredentialsMatcher.setHashIterations(PasswordHelper.HASH_ITERATIONS);//散列次数
		return hashedCredentialsMatcher;
	}
	
	@Bean
	public EnceladusShiroRealm shiroRealm() {
		EnceladusShiroRealm enceladusShiroRealm=new EnceladusShiroRealm();
		enceladusShiroRealm.setCredentialsMatcher(hashedCredentialsMatcher());
		return enceladusShiroRealm;
	}
	
	@Bean
	public SecurityManager securityManager() {
		DefaultSecurityManager defaultSecurityManager=new DefaultSecurityManager();
		defaultSecurityManager.setRealm(shiroRealm());
		return defaultSecurityManager;
	}
	
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
		ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		
		Map<String,String> filterChainDefinitionMap=new HashMap<String,String>();
		shiroFilterFactoryBean.setLoginUrl("/login");
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthc");
		shiroFilterFactoryBean.setSuccessUrl("/home/index");
		
		filterChainDefinitionMap.put("/*", "anon");
        filterChainDefinitionMap.put("/authc/index", "authc");
        filterChainDefinitionMap.put("/authc/admin", "roles[admin]");
        filterChainDefinitionMap.put("/authc/renewable", "perms[Create,Update]");
        filterChainDefinitionMap.put("/authc/removable", "perms[Delete]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		
		return shiroFilterFactoryBean;
		
	}
}




















