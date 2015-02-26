package com.mylab;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.web.client.RestTemplate;

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
