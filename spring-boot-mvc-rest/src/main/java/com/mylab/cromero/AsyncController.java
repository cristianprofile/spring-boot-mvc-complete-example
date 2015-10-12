package com.mylab.cromero;

import java.util.List;
import java.util.concurrent.Callable;

import com.mylab.cromero.cromero.dto.BaseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mylab.cromero.service.BaseService;


/**
 * Rest async controller pizzas example.
 * <p/>
 * Simple example for a callable async rest service.
 * <p/>
 */

@RestController
@RequestMapping("/baseasinc")
public class AsyncController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BaseService baseService;


	@RequestMapping(method = RequestMethod.GET)
	public Callable<List<BaseResponse>> listAllBase() {

		// logger.debug("paginación recibida :{}",pageable);
		List<BaseResponse> findAllBases = baseService.findAllBases();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");	
		return ()-> findAllBases;
	}

	

}
