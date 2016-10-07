package com.mylab.cromero.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mylab.cromero.repository.BaseRepository;
import com.mylab.cromero.repository.domain.Base;
import com.mylab.cromero.repository.dto.BaseRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class RestTestIT {


    @Autowired
    private BaseRepository baseRepository;

    @Autowired
    private MockMvc mockMvc;


    @Test
    @Transactional
    public void createBases() throws Exception {

        final BaseRequest baseRequest = new BaseRequest();
        baseRequest.setName("new base");
        String baseJson = json(baseRequest);
        this.mockMvc.perform(post("/base").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(baseJson)).andExpect(status().isCreated());
    }

    private String json(Object o) throws IOException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        return ow.writeValueAsString(o);
    }

    @Test
    @Transactional
    public void getBases() throws Exception {
        Base baseAlmacenar = new Base();
        baseAlmacenar.setName("margarita");
        baseRepository.save(baseAlmacenar);

        baseAlmacenar = new Base();
        baseAlmacenar.setName("masa pan");
        baseRepository.save(baseAlmacenar);

        mockMvc.perform(get("/base/")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", equalToIgnoringCase("margarita")))
                .andExpect(jsonPath("$[1].name", equalToIgnoringCase("masa pan")));

    }

    @Test
   @Transactional
    public void getBasesAsync() throws Exception {
        Base baseAlmacenar = new Base();
        baseAlmacenar.setName("margarita");
        baseRepository.save(baseAlmacenar);

        baseAlmacenar = new Base();
        baseAlmacenar.setName("masa pan");
        baseRepository.save(baseAlmacenar);

        MvcResult mvcResult = mockMvc.perform(get("/baseasinc/"))
                .andExpect(request().asyncStarted())
                .andReturn();

        mvcResult.getAsyncResult();

        mockMvc.perform(asyncDispatch(mvcResult)).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", equalToIgnoringCase("margarita")))
                .andExpect(jsonPath("$[1].name", equalToIgnoringCase("masa pan")));

    }


}
