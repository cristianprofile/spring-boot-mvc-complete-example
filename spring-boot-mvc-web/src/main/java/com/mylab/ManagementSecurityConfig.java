package com.mylab;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

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
