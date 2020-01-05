package com.example.demo;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import com.example.demo.entity.User;

public class PasswordHelper {

	private RandomNumberGenerator randomNumberGenerator=new SecureRandomNumberGenerator();
	public static final String ALGORITHM_NAME="md5";//基础散列算法
	public static final int HASH_ITERATIONS=2;//散列次数
	
	public void encryptPassword(User user) {
		user.setSalt(randomNumberGenerator.nextBytes().toHex());
		String newPassword=new SimpleHash(ALGORITHM_NAME, user.getPassword(), 
					ByteSource.Util.bytes(user.getSalt()), HASH_ITERATIONS).toHex();
		user.setPassword(newPassword);
	}
}
