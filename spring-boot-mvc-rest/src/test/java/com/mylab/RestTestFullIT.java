package com.mylab;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.neo4j.cypher.internal.compiler.v2_1.ast.rewriters.isolateAggregation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;






import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mylab.cromero.domain.Base;
import com.mylab.cromero.dto.BaseRequest;
import com.mylab.cromero.dto.BaseResponse;
import com.mylab.cromero.repository.BaseRepository;


//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
//@WebIntegrationTest("server.port:0") //watch an empty port to run test
//@Transactional
public class RestTestFullIT {

	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BaseRepository baseRepository;

	private RestTemplate restTemplate = new TestRestTemplate();
	
	@Value("${local.server.port}")
    private int port;
	



//	@Test
//	public void createBases() throws Exception {
//
//		BaseRequest base = new BaseRequest();
//		base.setName("newbase");	
//		String response = restTemplate.postForObject(
//					"http://localhost:"+port+"/base", base, String.class);
//		System.out.println(response);
//			// URI postForLocation = restTemplate.postForLocation("/base", base);
//
//	}

//	@Test
//	public void getBases() throws Exception {
//		//TODO TEST WITH REPOSITORY ACCESS BLOCK TEST EXECUTION
//		logger.debug("PORT:"+port);
// 
//		Base baseAlmacenar = new Base();
//
//		baseAlmacenar = new Base();
//		baseAlmacenar.setName("masa pan");
//		baseRepository.save(baseAlmacenar);

//		BaseResponse[] bases = restTemplate.getForObject(
//				"http://localhost:"+port+"/base", BaseResponse[].class);
//
//		Assert.assertTrue(bases.length == 0);
//		Assert.assertTrue(bases[0].getName().equals("margarita"));

//	}

}
