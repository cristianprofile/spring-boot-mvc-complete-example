 package com.mylab.cromero.controller.controller;

 import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
 import org.springframework.boot.web.servlet.ErrorPage;
 import org.springframework.boot.web.servlet.FilterRegistrationBean;
 import org.springframework.context.MessageSource;
 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.context.support.ResourceBundleMessageSource;
 import org.springframework.http.HttpStatus;
 import org.springframework.web.filter.CharacterEncodingFilter;
 import org.springframework.web.servlet.LocaleResolver;
 import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
 import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
 import org.springframework.web.servlet.i18n.SessionLocaleResolver;
 import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
 import org.springframework.web.servlet.view.tiles3.TilesView;
 import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

 import java.util.Locale;

 /**
  * <h1>WebConfig!</h1> Pizza app web config.
  * <p>
  * <b>WebConfig</b> this class give contains @bean used to config Spring mvc with tiles config.
  *
  * @author Cristian Romero Matesanz
  *
  *
  */
 @Configuration
 public class WebConfig extends WebMvcConfigurerAdapter {

     /**
      *
      * @return registrationBean with Utf-8 encoding config
      */
     @Bean
     public FilterRegistrationBean filterRegistrationBean() {
         FilterRegistrationBean registrationBean = new FilterRegistrationBean();
         CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
         characterEncodingFilter.setEncoding("UTF-8");
         registrationBean.setFilter(characterEncodingFilter);
         return registrationBean;
     }

     /**
      *
      * @return resolver with tiles config
      */
     @Bean
     public TilesViewResolver setupViewTilesResolver() {
         TilesViewResolver resolver = new TilesViewResolver();
         resolver.setViewClass(TilesView.class);
         return resolver;
     }

     /**
      *
      * @return tiles configure layers with every views of pizza app
      */
     @Bean
     public TilesConfigurer setupTilesConfigurer() {
         TilesConfigurer configurer = new TilesConfigurer();
         configurer.setDefinitions("classpath:layouts/layouts.xml",
                 "classpath:layouts/views.xml");
         configurer.setCheckRefresh(true);
         return configurer;
     }

      /**
      * Configures the message source bean.
      * @return message source associated to pizza app
      */
     @Bean
     public MessageSource messageSource() {
         ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
         messageSource.setBasename("i18n/messages");
         messageSource.setUseCodeAsDefaultMessage(true);
         return messageSource;
     }

     /**
      * local resolver with default value (US)
      * @return local resolver
      */
     @Bean
     public LocaleResolver localeResolver() {
         SessionLocaleResolver slr = new SessionLocaleResolver();
         slr.setDefaultLocale(Locale.US);
         return slr;
     }

     /**
      * Set custom error pages
      * @return EmbeddedServletContainerCustomizer
      */
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

      /**
      * Add interceptor to allow pizza app to change language
       */
      @Override
      public void addInterceptors(InterceptorRegistry registry) {
          registry.addInterceptor(localeChangeInterceptor());
      }

     /**
      * set custom param of pizzas app to change locale with value "lang"
      * @return localchangeinterceptor of pizzas app
      */
     @Bean
     public LocaleChangeInterceptor localeChangeInterceptor() {
         LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
         lci.setParamName("lang");
         return lci;
     }
 }