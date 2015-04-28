 package com.mylab;

import java.io.IOException;
import java.util.Arrays;
import java.util.Locale;

import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import org.springframework.web.servlet.view.tiles3.TilesView;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;
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
     * Spring profiles can be configured with a program arguments --spring.profiles.active=prod
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
    
   
    
    
    @Bean
	public TilesViewResolver setupViewTilesResolver() {
		TilesViewResolver resolver = new TilesViewResolver();
		resolver.setViewClass(TilesView.class);
		return resolver;
	}
	
    
	@Bean
	public TilesConfigurer setupTilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[] {"/WEB-INF/layouts/layouts.xml", 
												"/WEB-INF/layouts/views.xml"});
		configurer.setCheckRefresh(true);
		return configurer;
	}
	
	 /**
     * Configures the message source bean.
     * @return
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("i18n/messages");
        messageSource.setUseCodeAsDefaultMessage(true);
        return messageSource;
    }

    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        return slr;
    }
 
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        return lci;
    }
 
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
	
    
	@Bean
	public EmbeddedServletContainerCustomizer containerCustomizer() {
	 
	   return (container -> {
		   
		    ErrorPage error400Page = new ErrorPage(HttpStatus.BAD_REQUEST, "/400.html");
	        ErrorPage error401Page = new ErrorPage(HttpStatus.UNAUTHORIZED, "/401.html");
	        ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403.html");
	        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
	        ErrorPage error405Page = new ErrorPage(HttpStatus.METHOD_NOT_ALLOWED, "/405.html");
	        ErrorPage error500Page = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/500.html");
	 
	        container.addErrorPages(error401Page, error403Page, error404Page, error405Page, error500Page,error400Page);
	   });
	}
} 