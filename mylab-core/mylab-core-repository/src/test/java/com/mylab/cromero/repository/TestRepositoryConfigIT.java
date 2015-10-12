package com.mylab.cromero.repository;

import com.mylab.cromero.repository.config.ConfigurationRepository;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by cristianromeromatesanz on 11/10/15.
 */


@Configuration
@EnableAutoConfiguration
@Import({ConfigurationRepository.class})
//use to test our class with Spring boot  autoconfig
public class TestRepositoryConfigIT {
}
