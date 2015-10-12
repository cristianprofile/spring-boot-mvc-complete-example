package com.mylab.cromero.controller.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
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
@EnableWebMvcSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	private UserDetailsService userDetailsServiceImpl;
	
	
	
	 @Bean
	    public RoleHierarchyImpl roleHierarchy() {
	        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
	        roleHierarchy.setHierarchy("ROLE_ADMIN > ROLE_USER");
	        return roleHierarchy;
	    }
	
	
	/**
	 * Configure Spring security url filters and login logout Spring Security
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		 http
		    .authorizeRequests()
		        .antMatchers("/pizzas","/info","/addPizza").hasAnyRole("USER","ADMIN")
		        .antMatchers("/users","/addUser").hasRole("ADMIN")
		        .antMatchers("/static/**","/logout","/login").permitAll()
		        .and()
		    .formLogin()
		        .loginPage("/login")
		        .failureUrl("/login?error")
		        .permitAll()
		        .and()
		    .logout()
		        .logoutSuccessUrl("/?logout")
		        .deleteCookies("remember-me")
		        .permitAll();
	}
	
	
	

	/**
	 * Configure global security with Bccyptenoncder and custom userDetailService with Spring Security
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
	}
	
	
	/**
	 * Bcrypt password encoding configuration, more info at http://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
}
