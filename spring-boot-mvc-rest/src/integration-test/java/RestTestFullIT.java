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
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest("server.port:0") //watch an empty port to run test

public class RestTestFullIT {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestTemplate restTemplate = new TestRestTemplate();

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
        //TODO TEST WITH REPOSITORY ACCESS BLOCK TEST EXECUTION
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
