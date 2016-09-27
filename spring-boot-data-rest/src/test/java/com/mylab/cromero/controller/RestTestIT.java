package com.mylab.cromero.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mylab.cromero.repository.BaseRepository;
import com.mylab.cromero.repository.domain.Base;
import com.mylab.cromero.repository.dto.BaseRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.charset.Charset;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;


//   The @SpringApplicationConfiguration annotation is similar to the @ContextConfiguration annotation in that it
//   is used to specify which application context(s) that should be used 
//   in the test. Additionally, it will trigger logic for reading Spring Boot 
//   specific configurations, properties, and so on.

//   @WebAppConfiguration must be present in order to tell Spring that a 
//   WebApplicationContext should be loaded for the test. 
//   It also provides an attribute for specifying the path to the root of 
//   the web application.
//   full example at http://spring.io/guides/tutorials/bookmarks/

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration

@Transactional
public class RestTestIT {

    @Autowired
    BaseRepository baseRepository;

    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Value("${spring.data.rest.base-path}")
    private String basePath;

    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType("application", "hal+json", Charset.forName("utf8"));


    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void testHome() throws Exception {

        this.mockMvc.perform(get(basePath)).andExpect(status().isOk())
                .andExpect(content().string(containsString("bases")));
    }


    @Test
    public void createBases() throws Exception {

        final BaseRequest baseRequest = new BaseRequest();
        baseRequest.setName("new base");
        String baseJson = json(baseRequest);
        this.mockMvc.perform(post(basePath + "/bases").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(baseJson)).andExpect(status().isCreated());
    }

    private String json(Object o) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(o);
    }

    @Test
    public void getBases() throws Exception {
        Base baseAlmacenar = new Base();
        baseAlmacenar.setName("margarita");
        baseRepository.save(baseAlmacenar);

        baseAlmacenar = new Base();
        baseAlmacenar.setName("masa pan");
        baseRepository.save(baseAlmacenar);



        mockMvc.perform(get(basePath + "/bases").contentType(APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$._embedded.bases", hasSize(2)))
                .andExpect(jsonPath("$._embedded.bases[0].name", equalToIgnoringCase("margarita")))
                .andExpect(jsonPath("$._embedded.bases[1].name", equalToIgnoringCase("masa pan")));

    }


}
