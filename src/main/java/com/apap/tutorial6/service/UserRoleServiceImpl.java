package com.apap.tutorial6.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.apap.tutorial6.model.UserRoleModel;
import com.apap.tutorial6.repository.UserRoleDb;

@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDb userDb;

	@Override
	public UserRoleModel addUser(UserRoleModel user) {
		// TODO Auto-generated method stub
		String pass = encrypt(user.getPassword());
		user.setPassword(pass);
		return userDb.save(user);
	}

	@Override
	public String encrypt(String password) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(password);
		return hashedPassword;
	}

	@Override
	public UserRoleModel updatePassword(String username, String oldPass, String newPass) {
		// TODO Auto-generated method stub
		UserRoleModel user = userDb.findByUsername(username);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(encoder.matches(oldPass, user.getPassword())) {
			String pass = encrypt(newPass);
			System.out.println(user.getPassword()+" yaeay 12345 "+user.getUsername());
			user.setPassword(pass);
			System.out.println(pass);
			System.out.println(user.getPassword()+" yaeay 123 "+user.getUsername());
			userDb.save(user);
			System.out.println(user.getPassword()+" yaeay "+user.getUsername());
			return user;
		}
		System.out.println(encoder.matches(oldPass, user.getPassword()));
		System.out.println(user.getPassword()+ " cekker " + encrypt(oldPass));
		return null;
	}

}
