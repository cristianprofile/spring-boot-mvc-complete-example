package com.mylab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mylab.cromero.domain.Base;


@SpringBootApplication
@EnableJpaRepositories
@EntityScan(basePackageClasses=Base.class)
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

} 