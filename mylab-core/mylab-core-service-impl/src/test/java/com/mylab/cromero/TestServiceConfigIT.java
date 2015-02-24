package com.mylab.cromero;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.subethamail.wiser.Wiser;

@Configuration
@SpringBootApplication
public class TestServiceConfigIT {
	
	
	@Value("${spring.mail.host}")
	private String host;
	
	@Value("${spring.mail.port}")
	private int port;
	
	/**
	 * Bcrypt password encoding configuration, more info at http://www.baeldung.com/spring-security-registration-password-encoding-bcrypt
	 * @return
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Bean
	public Wiser wiser() {
		Wiser wiser = new Wiser();
		wiser.setHostname(host);
		wiser.setPort(port);
	    return wiser ;
	}

}
