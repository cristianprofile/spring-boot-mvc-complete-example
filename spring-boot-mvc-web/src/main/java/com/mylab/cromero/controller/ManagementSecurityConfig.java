package com.mylab.cromero.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


/**
 * <h1>ManagementSecurityConfig!</h1> Spring security filter manage url.
 * <p>
 * <b>ManagementSecurityConfig</b> Spring security filter to limit access to manage app of Spring Actuator layer.
 *
 * @author Cristian Romero Matesanz
 *
 * 
 */
@Order(0)
@Configuration
public class ManagementSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
         http
            .requestMatchers()
                .requestMatchers(request -> "/manage".equals(request.getContextPath()))
                .and()
            .authorizeRequests()
                .anyRequest().hasRole("ADMIN")
                .and()
            .httpBasic();
    }
}
