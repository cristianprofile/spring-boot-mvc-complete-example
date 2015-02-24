package com.mylab.cromero.repository;

import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.mylab.cromero.domain.User;

@Configuration
@EnableJpaRepositories(basePackageClasses = UserRepository.class)
@EntityScan(basePackageClasses = User.class)
@ComponentScan("com.mylab")
@Import({ DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class })
public class TestRepositoryConfigIT {

}
