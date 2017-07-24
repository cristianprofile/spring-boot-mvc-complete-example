package com.mylab.cromero.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.mylab.cromero.repository.dto.BaseRequest;
import com.mylab.cromero.repository.dto.BaseResponse;
import com.mylab.cromero.service.BaseService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
public class RestMockMvcUnitTest {


    private MockMvc mockMvc;

    @Mock
    private BaseService baseService;


    @InjectMocks
    private HelloWorldController helloWorldController;


    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(helloWorldController).
                setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver()).build();
    }


    @Test
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
    public void getBases() throws Exception {
        List<BaseResponse> findAllBases = new ArrayList<BaseResponse>();
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setId(5l);
        baseResponse.setName("basic");
        findAllBases.add(baseResponse);

        baseResponse = new BaseResponse();
        baseResponse.setId(5l);
        baseResponse.setName("probase");

        findAllBases.add(baseResponse);

        when(this.baseService.findAllBases()).thenReturn(
                findAllBases);

        mockMvc.perform(get("/base/")).andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$", hasSize(2))).
                andExpect(jsonPath("$.[0].name", equalToIgnoringCase("basic")))
                .andExpect(jsonPath("$.[1].name", equalToIgnoringCase("probase")));

        verify(this.baseService, times(1)).findAllBases();


    }

    @Test
    public void getBases2() throws Exception {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setId(5l);
        baseResponse.setName("basic");

        when(this.baseService.getBase(3l)).thenReturn(
                baseResponse);

        mockMvc.perform(get("/base/{var}", 3)).andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalToIgnoringCase("basic")));

        verify(this.baseService, times(1)).getBase(3l);

    }


}
