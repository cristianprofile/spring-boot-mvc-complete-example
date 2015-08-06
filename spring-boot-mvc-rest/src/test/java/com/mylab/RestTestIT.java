package com.mylab;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.transaction.Transactional;

import com.mylab.cromero.dto.BaseRequest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mylab.cromero.domain.Base;
import com.mylab.cromero.repository.BaseRepository;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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

    private MediaType contentType = new MediaType(
            MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private WebApplicationContext webApplicationContext;


    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void createBases() throws Exception {

        final BaseRequest baseRequest = new BaseRequest();
        baseRequest.setName("new base");
        String baseJson = json(baseRequest);
        this.mockMvc.perform(post("/base").contentType(contentType)
                .content(baseJson)).andExpect(status().isCreated());
    }


    @Test
    public void getBases() throws Exception {
        Base baseAlmacenar = new Base();
        baseAlmacenar.setName("margarita");
        baseRepository.save(baseAlmacenar);

        baseAlmacenar = new Base();
        baseAlmacenar.setName("masa pan");
        baseRepository.save(baseAlmacenar);

        mockMvc.perform(get("/base/")).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", equalToIgnoringCase("margarita")))
                .andExpect(jsonPath("$[1].name", equalToIgnoringCase("masa pan")));

    }


    protected String json(Object o) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(o);
        return json;
    }


    @Test
    public void getBasesAsync() throws Exception {
        Base baseAlmacenar = new Base();
        baseAlmacenar.setName("margarita");
        baseRepository.save(baseAlmacenar);

        baseAlmacenar = new Base();
        baseAlmacenar.setName("masa pan");
        baseRepository.save(baseAlmacenar);

        MvcResult mvcResult = mockMvc.perform(get("/baseasinc/").accept(MediaType.parseMediaType("application/json;charset=UTF-8")))
                .andExpect(request().asyncStarted())
                .andReturn();

        mvcResult.getAsyncResult();

        mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk())
                .andExpect(content().contentType(contentType))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", equalToIgnoringCase("margarita")))
                .andExpect(jsonPath("$[1].name", equalToIgnoringCase("masa pan")));

    }


}
