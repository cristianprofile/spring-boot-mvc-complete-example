package com.mylab.cromero.controller;

import com.mylab.cromero.repository.config.ConfigurationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;

/**
 * <h1>Main Application!</h1> Application main class of Spring boot app.
 * <p>
 * <b>Application</b> Main Application of Spring boot app with custom log of Spring profile run
 *
 * @author Cristian Romero Matesanz
 */
@SpringBootApplication
@Import({ConfigurationRepository.class})
public class Application {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        //do not show Spring boot banner when boot starts!!!!!
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }


    /**
     * Initializes pizzas example.
     * <p>
     * Spring profiles can be run with maven profile (example mvn -Pdevelop spring-boot:run  ). See pom.xml for more information (develop and prod profile).
     * <p>
     */
    @PostConstruct
    public void initApplication() throws IOException {
        if (env.getActiveProfiles().length == 0) {
            logger.warn("No Spring profile configured, running with default configuration with profile develop");
        }
        else {
            logger.info("Running with Spring profile(s) : {}", Arrays.toString(env.getActiveProfiles()));
        }
    }


}