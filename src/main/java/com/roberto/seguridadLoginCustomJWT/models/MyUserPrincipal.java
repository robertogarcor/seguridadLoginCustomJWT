package com.roberto.seguridadLoginCustomJWT.models;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class MyUserPrincipal implements UserDetails {
	
	private String username;
	private String password;
	private String surname;
	private String email;
	private Collection<? extends GrantedAuthority> authorities;
	//private Collection<? extends GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(RoleType.ROLE_USER + ""));

	public MyUserPrincipal(String username, String password, String surname, String email, Collection<? extends GrantedAuthority> authorities) {
		this.username = username;
		this.password = password;
		this.surname = surname;
		this.email = email;
		this.authorities = authorities;
	}

	public static MyUserPrincipal build(User user) {
		List<GrantedAuthority> authorities =
				user.getRoles().stream().map(role -> new SimpleGrantedAuthority(
						role.getRolename().name())).collect(Collectors.toList());
		return new MyUserPrincipal(user.getUsername(), user.getPassword(), user.getSurname(), user.getEmail(), authorities);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}
	
	public String getSurname() {
		return surname;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


}
