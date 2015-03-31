package com.mylab.cromero.service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mylab.cromero.dto.UserResponse;

public class CustomUserDetails implements UserDetails {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserResponse user;
	
	private String password;
	
	private List<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();
	
	
	public CustomUserDetails(UserResponse user,List<String> roles, String password) {
		this.user = user;
		this.password=password;
		authorities = roles.parallelStream().map(roleName->new SimpleGrantedAuthority(roleName)).collect(Collectors.toList());
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		    return authorities;
		
	}
 
	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.user.getUser();
	}

	@Override
	public boolean isAccountNonExpired() {
		return this.user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		return this.user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return this.user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		return this.user.isEnabled();
	}

	
}
