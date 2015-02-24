package com.mylab;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mylab.cromero.dto.BaseRequest;
import com.mylab.cromero.dto.BaseResponse;
import com.mylab.cromero.exception.BaseNotFoundException;
import com.mylab.cromero.service.BaseService;

@RestController
@RequestMapping("/base")
public class HelloWorldController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private BaseService baseService;

	// example of calling with pageable
	// http://localhost:8080/SpringMVC/base?sort=firstname&sort=lastname,asc&size=444&page=22
	@RequestMapping(method = RequestMethod.GET)
	public List<BaseResponse> listAllBase(
			@PageableDefault(size = 50, page = 2) Pageable pageable) {

		// logger.debug("paginación recibida :{}",pageable);
		List<BaseResponse> findAllBases = baseService.findAllBases();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "application/json; charset=utf-8");
		return findAllBases;

	}

	@RequestMapping(value = "/{baseId}", method = RequestMethod.GET)
	public BaseResponse getBase(@PathVariable("baseId") long id) {
		BaseResponse base = baseService.getBase(id);
		return base;
	}

	@RequestMapping(value = "/{baseId}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBase(@PathVariable("baseId") long id) {
		baseService.deleteBase(id);
	}

	@RequestMapping(method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public void insertBase(@RequestBody BaseRequest newBase) {

		BaseRequest base = new BaseRequest();
		base.setName(newBase.getName());
		baseService.saveBase(base);

	}

	@RequestMapping(method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.OK)
	public void updateBase(@RequestBody BaseRequest base) {

		BaseRequest baseUpdate = new BaseRequest();
		baseUpdate.setId(base.getId());
		baseUpdate.setName(base.getName());
		baseService.updateBase(baseUpdate);

	}

	// Convert a predefined exception to an HTTP Status code Not Found
	// @ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Base not found")
	@ExceptionHandler(BaseNotFoundException.class)
	public ErrorResponse notFound(BaseNotFoundException exception) {
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage("base not found");
		errorResponse.setCode("404");
		return errorResponse;

	}

	// @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE, reason =
	// "service not available")
	@ExceptionHandler(Exception.class)
	public ErrorResponse error(Exception exception) {
		exception.printStackTrace();
		ErrorResponse errorResponse = new ErrorResponse();
		errorResponse.setMessage(exception.getMessage());
		errorResponse.setCode("208");
		return errorResponse;
	}

}
