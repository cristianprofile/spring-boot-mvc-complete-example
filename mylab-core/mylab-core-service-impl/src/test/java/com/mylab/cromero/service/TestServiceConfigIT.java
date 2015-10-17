package com.mylab.cromero.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.subethamail.wiser.Wiser;

@Configuration
@EnableAutoConfiguration
@Import({ConfigurationService.class})
public class TestServiceConfigIT {


	@Value("${spring.mail.host}")
	private String host;

	@Value("${spring.mail.port}")
	private int port;


	@Bean
	public Wiser wiser() {
		Wiser wiser = new Wiser();
		wiser.setHostname(host);
		wiser.setPort(port);
		return wiser;
	}

}
