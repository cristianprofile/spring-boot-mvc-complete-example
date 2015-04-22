package com.mylab.cromero;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mylab.cromero.dto.BaseRequest;
import com.mylab.cromero.dto.BaseResponse;
import com.mylab.cromero.exception.BaseNotFoundException;
import com.mylab.cromero.service.BaseService;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TestServiceConfigIT.class)
public class BaseServiceImplTestIT {

	@Autowired
	private BaseService baseService;

	@Transactional
	@Test(expected = BaseNotFoundException.class)
	public void testDeleteBaseNotExist() {

		BaseRequest base = new BaseRequest();
		base.setName("margarita");
		baseService.deleteBase(base);

	}

	@Transactional
	@Test
	public void testDeleteBaseOk() {

		BaseRequest base = new BaseRequest();
		base.setName("margarita");
		baseService.saveBase(base);
		assertThat(baseService.findAllBases(), hasSize(1));
		baseService.deleteBase(base);
		assertThat(baseService.findAllBases(), empty());
	}

	@Transactional
	@Test
	public void testFindAllSortedOk() {

		BaseRequest baseMargarita = new BaseRequest();

		baseMargarita.setName("margarita");
		baseService.saveBase(baseMargarita);

		BaseRequest basePinya = new BaseRequest();
		basePinya.setName("piña");
		baseService.saveBase(basePinya);

		BaseRequest baseAtun = new BaseRequest();
		baseAtun.setName("atun");
		baseService.saveBase(baseAtun);

		BaseRequest baseCodillo = new BaseRequest();
		baseCodillo.setName("codillo");
		baseService.saveBase(baseCodillo);

		List<BaseResponse> findAllBasesSorted = baseService.findAllBasesSorted();

		assertThat(findAllBasesSorted, hasSize(4));
		
		// test if all values is in my collection and if order is correct

		List<BaseRequest> collectRequest = findAllBasesSorted.stream().map(baseResponse -> {
			BaseRequest baseRequest = new BaseRequest();
			baseRequest.setName(baseResponse.getName());
			baseRequest.setId(baseResponse.getId());
			return baseRequest;
		}).collect(Collectors.toList());

		assertThat(collectRequest,contains(baseAtun, baseCodillo, baseMargarita, basePinya));

	}

	
	
	 @Transactional
	 @Test
	 public void testFindAllBasesPaginationAndSortOk() {
	
		BaseRequest baseMargarita = new BaseRequest();

		baseMargarita.setName("margarita");
		baseService.saveBase(baseMargarita);

		BaseRequest basePinya = new BaseRequest();
		basePinya.setName("piña");
		baseService.saveBase(basePinya);

		BaseRequest baseAtun = new BaseRequest();
		baseAtun.setName("atun");
		baseService.saveBase(baseAtun);

		BaseRequest baseCodillo = new BaseRequest();
		baseCodillo.setName("codillo");
		baseService.saveBase(baseCodillo);
	
		List<BaseResponse> findAllBasesSorted = baseService.findAllBasesPaginationAndSorted(0);
		List<BaseRequest> collectRequest = findAllBasesSorted.stream().map(baseResponse -> {
			BaseRequest baseRequest = new BaseRequest();
			baseRequest.setName(baseResponse.getName());
			baseRequest.setId(baseResponse.getId());
			return baseRequest;
		}).collect(Collectors.toList());
	 
	 assertThat(collectRequest, hasSize(2));
	 
	 assertThat(collectRequest,contains(baseAtun, baseCodillo));
	
	 findAllBasesSorted = baseService.findAllBasesPaginationAndSorted(1);
	 assertThat(collectRequest, hasSize(2));
	 assertThat(collectRequest,contains(baseMargarita, basePinya));
	
	 findAllBasesSorted = baseService.findAllBasesPaginationAndSorted(2);
	 assertThat(collectRequest, hasSize(0));
	 }
	
	 @Transactional
	 @Test
	 public void testFindAllOptional() {
	
	 BaseRequest base = new BaseRequest();
	
	 base.setName("margarita");
	 baseService.saveBase(base);
	
	 base.setName("piña");
	 baseService.saveBase(base);
	
	 base.setName("atun");
	 baseService.saveBase(base);
	
	 base.setName("codillo");
	 baseService.saveBase(base);
	
	 Optional<BaseResponse> findById = baseService.findById(130L);
	 
	 assertThat(findById.isPresent(),equalTo(true));
	
	 }

}
