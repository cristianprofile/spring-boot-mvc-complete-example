package com.mylab.cromero.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mylab.cromero.repository.dto.BaseRequest;
import com.mylab.cromero.repository.dto.BaseResponse;
import com.mylab.cromero.service.BaseService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
//Spring boot test is searching  @SpringBootConfiguration or @SpringBootApplication
//In this case it will automaticaly find Application boot main class
@AutoConfigureMockMvc
public class RestTestIT {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BaseService baseService;


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

        List<BaseResponse> baseResponses = new ArrayList<BaseResponse>();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setName("margarita");
        baseResponse.setId(1l);
        baseResponses.add(baseResponse);
        baseResponse = new BaseResponse();
        baseResponse.setName("masa pan");
        baseResponse.setId(1l);
        baseResponses.add(baseResponse);


        given(baseService.findAllBases()).willReturn(baseResponses);

        mockMvc.perform(get("/base/")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name", equalToIgnoringCase("margarita")))
                .andExpect(jsonPath("$[1].name", equalToIgnoringCase("masa pan")));

    }

    @Test
    @Transactional
    public void getBasesAsync() throws Exception {

        List<BaseResponse> baseResponses = new ArrayList<BaseResponse>();

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setName("margarita");
        baseResponse.setId(1l);
        baseResponses.add(baseResponse);
        baseResponse = new BaseResponse();
        baseResponse.setName("masa pan");
        baseResponse.setId(1l);
        baseResponses.add(baseResponse);

        given(baseService.findAllBases()).willReturn(baseResponses);

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
