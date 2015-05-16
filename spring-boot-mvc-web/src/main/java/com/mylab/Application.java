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

/**
 * <h1>Main Application!</h1> Application main class of Spring boot app.
 * <p>
 * <b>Application</b> Main Application of Spring boot app with custom log of Spring profile run
 *
 * @author Cristian Romero Matesanz
 * 
 *
 * 
 */

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
     * Spring profiles can be run with maven profile (example mvn -Pdevelop spring-boot:run  ). 
     * See pom.xml for more information (develop and prod profile).
     * @see pom.xml
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