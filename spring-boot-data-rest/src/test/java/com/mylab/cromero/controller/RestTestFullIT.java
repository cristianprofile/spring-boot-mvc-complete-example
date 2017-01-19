package com.mylab.cromero.controller;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mylab.cromero.repository.BaseRepository;
import com.mylab.cromero.repository.domain.Base;
import com.mylab.cromero.repository.dto.BaseRequest;
import com.mylab.cromero.repository.dto.BaseResponse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.hal.Jackson2HalModule;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.hateoas.MediaTypes.HAL_JSON;


@RunWith(SpringRunner.class)
//Spring boot test is searching  @SpringBootConfiguration or @SpringBootApplication
//In this case it will automaticaly find Application boot main class
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)

public class RestTestFullIT {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    BaseRepository baseRepository;

    @Value("${local.server.port}")
    private int port;


    @Value("${spring.data.rest.base-path}")
    private String basePath;


    @Before
    public void setUpBaseClass() {
        //before any test delete all database
        baseRepository.deleteAll();
    }


    @Test
    public void createBases() throws Exception {

        BaseRequest base = new BaseRequest();
        base.setName("newbase");
        String response = restTemplate.postForObject("http://localhost:" + port + basePath + "/bases", base, String.class);
        base.setName("newbase3");
        restTemplate.postForObject("http://localhost:" + port + basePath + "/bases", base, String.class);
        ResponseEntity<PagedResources<BaseResponse>> responseEntity = getRestTemplateWithHalMessageConverter().exchange(
                "http://localhost:" + port + basePath + "/bases", HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<BaseResponse>>() {});
        assertEquals(responseEntity.getBody().getMetadata().getTotalElements(), 2);
        assertTrue(responseEntity.getBody().getContent().stream().anyMatch(a->a.getName().equals("newbase")));
        assertTrue(responseEntity.getBody().getContent().stream().anyMatch(a->a.getName().equals("newbase3")));
        assertTrue(responseEntity.getBody().getContent().stream().noneMatch(a -> a.getName().equals("newbase4")));
    }

    // this method create a custom rest template to be able to consume HATEAOS rest services
    public RestTemplate getRestTemplateWithHalMessageConverter() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new Jackson2HalModule());

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(HAL_JSON));
        converter.setObjectMapper(mapper);

        RestTemplate template = new RestTemplate(Collections.singletonList(converter));
        return template;
    }

    @Test
    public void getBases() throws Exception {

        Base baseAlmacenar = new Base();

        baseAlmacenar.setName("big");
        baseRepository.save(baseAlmacenar);

        ResponseEntity<PagedResources<BaseResponse>> responseEntity = getRestTemplateWithHalMessageConverter().exchange(
                "http://localhost:" + port + basePath + "/bases", HttpMethod.GET, null,
                new ParameterizedTypeReference<PagedResources<BaseResponse>>() {});

        assertEquals(responseEntity.getBody().getMetadata().getTotalElements(), 1);
        assertTrue(responseEntity.getBody().getContent().stream().anyMatch(a -> a.getName().equals("big")));
        assertTrue(responseEntity.getBody().getContent().stream().noneMatch(a -> a.getName().equals("newbase4")));




    }



}
