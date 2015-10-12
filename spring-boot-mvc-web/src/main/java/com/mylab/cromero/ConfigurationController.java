package com.mylab.cromero;


import com.mylab.cromero.cromero.domain.Base;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by cristianromeromatesanz on 11/10/15.
 * This class scan every components of our repository layer
 */

@Configuration
@ComponentScan({"com.mylab.cromero.controller"})
public class ConfigurationController {

}
