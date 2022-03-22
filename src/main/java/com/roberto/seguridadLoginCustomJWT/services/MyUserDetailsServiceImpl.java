package com.roberto.seguridadLoginCustomJWT.services;

import com.roberto.seguridadLoginCustomJWT.models.User;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.roberto.seguridadLoginCustomJWT.models.MyUserPrincipal;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userService.getByUsername(username);
		return MyUserPrincipal.build(user.get());
	}
}
