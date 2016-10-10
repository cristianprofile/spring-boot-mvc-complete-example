package com.mylab.cromero.controller;

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
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
//Spring boot test is searching  @SpringBootConfiguration or @SpringBootApplication
//In this case it will automaticaly find Application boot main class
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)

public class RestTestFullIT {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BaseRepository baseRepository;

    @Value("${local.server.port}")
    private int port;


    @Before
    public void setUpBaseClass() {
        //before any test delete all database
        baseRepository.deleteAll();
    }


    @Test
    public void createBases() throws Exception {

        BaseRequest base = new BaseRequest();
        base.setName("newbase");
        String response = restTemplate.postForObject(
                "http://localhost:" + port + "/base", base, String.class);
        BaseResponse[] bases = restTemplate.getForObject(
                "http://localhost:" + port + "/base", BaseResponse[].class);
        assertEquals(bases.length, 1);
        assertEquals(bases[0].getName(), "newbase");

    }

    @Test
    public void getBases() throws Exception {

        logger.debug("PORT:" + port);

        Base baseAlmacenar = new Base();

        baseAlmacenar.setName("masa pan");
        baseRepository.save(baseAlmacenar);

        BaseResponse[] bases = restTemplate.getForObject(
                "http://localhost:" + port + "/base", BaseResponse[].class);

        assertEquals(1, bases.length);
        assertEquals("masa pan", bases[0].getName());

    }


}
