package com.mylab.cromero.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.mylab.cromero.repository.dto.BaseResponse;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.mylab.cromero.service.BaseServiceImpl;


@RunWith(MockitoJUnitRunner.class)
public class RestTest {
	
	
	
	@Mock
	private BaseServiceImpl baseService;

	@InjectMocks
	private HelloWorldController helloWorldController;
	
	@Test
	public void testListAllBases() {

	
		List<BaseResponse> listaBases = new ArrayList<BaseResponse>();
		when(this.baseService.findAllBases()).thenReturn(listaBases);

		
		List<BaseResponse> listAllBase = helloWorldController.listAllBase(null);
		
		
		verify(this.baseService, times(1)).findAllBases();
		
		Assert.assertTrue(listAllBase.size()==0);
		
		

	}
	
	
	@Test
	public void testListAllBases2() {

	
		List<BaseResponse> listaBases = new ArrayList<BaseResponse>();
		BaseResponse baseResponse = new BaseResponse();
		baseResponse.setName("margarita");
		baseResponse.setId(2L);
		listaBases.add(baseResponse);
		when(this.baseService.findAllBases()).thenReturn(listaBases);

		
		List<BaseResponse> listAllBase = helloWorldController.listAllBase(null);
		
		
		verify(this.baseService, times(1)).findAllBases();
		
		Assert.assertTrue(listAllBase.size()==1);
		Assert.assertTrue(listAllBase.get(0).getName().equals("margarita"));
		
		

	}
	

}
