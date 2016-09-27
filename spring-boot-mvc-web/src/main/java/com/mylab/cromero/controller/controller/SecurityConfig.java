package com.mylab.cromero.controller.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


/**
 * <h1>WebSecurityConfig!</h1> Spring security filter pizza app.
 * <p>
 * <b>WebSecurityConfig</b> Spring security filter to limit access to pizza app.
 *
 * @author Cristian Romero Matesanz
 *
 * 
 */

@Configuration
public class SecurityConfig{
	
	
	/**
	 * Bcrypt password encoding configuration, more info at http://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
}
