 package com.mylab;

import java.io.IOException;
import java.util.Arrays;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.mylab.cromero.domain.Base;


@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackageClasses=Base.class)
public class Application extends WebMvcConfigurerAdapter {
	
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	 @Autowired
	 private Environment env;
	
	/**
     * Initializes pizzas example.
     * <p/>
     * Spring profiles can be configured with a program arguments Ex: -spring.profiles.active=prod
     * <p/>
     */
    @PostConstruct
    public void initApplication() throws IOException {
        if (env.getActiveProfiles().length == 0) {
        	logger.warn("No Spring profile configured, running with default configuration with profile develop");
        } else {
        	logger.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
        }
    }
	

    public static void main(String[] args) {

          SpringApplication app = new SpringApplication(Application.class);
          //do not show Spring boot banner when boot starts!!!!!
          app.setShowBanner(false);
          app.run(args);
    }
} 