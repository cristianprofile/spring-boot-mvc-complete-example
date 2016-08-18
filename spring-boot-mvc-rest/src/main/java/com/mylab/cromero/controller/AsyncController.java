package com.mylab.cromero.controller;

import com.mylab.cromero.repository.dto.BaseResponse;
import com.mylab.cromero.service.BaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.Callable;


/**
 * Rest async controller pizzas example.
 * <p>
 * Simple example for a callable async rest service.
 * <p>
 */

@RestController
@RequestMapping("/baseasinc")
public class AsyncController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private BaseService baseService;


    @RequestMapping(method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Callable<List<BaseResponse>> listAllBase() {

        List<BaseResponse> findAllBases = baseService.findAllBases();
        return () -> findAllBases;
    }


}
