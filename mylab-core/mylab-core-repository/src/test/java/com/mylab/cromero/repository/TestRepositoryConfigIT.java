package com.mylab.cromero.repository;

import com.mylab.cromero.repository.config.ConfigurationRepository;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Import;

/**
 * Created by cristianromeromatesanz on 11/10/15.
 */


/*
Indicates that a class provides Spring Boot application @Configuration. Can be used as an alternative to the Spring's standard @Configuration annotation so that configuration can be found automatically (for example in tests).
Application should only ever include one @SpringApplicationConfiguration and most idiomatic Spring Boot applications will inherit it from @SpringBootApplication.
*/

@SpringBootConfiguration
@Import({ConfigurationRepository.class})
//use to test our class with Spring boot  autoconfig
public class TestRepositoryConfigIT {
}
