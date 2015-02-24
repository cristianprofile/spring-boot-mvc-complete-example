package com.mylab.cromero.service.mapper;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mylab.cromero.dto.UserRequest;
import com.mylab.cromero.dto.UserResponse;

public class CustomUserDetails implements UserDetails {

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
		for (String roleName : roles) {
			authorities.add(new SimpleGrantedAuthority(roleName));
		}
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
		// TODO Auto-generated method stub
		return this.user.isAccountNonExpired();
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return this.user.isAccountNonLocked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return this.user.isCredentialsNonExpired();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return this.user.isEnabled();
	}

	

	





}
