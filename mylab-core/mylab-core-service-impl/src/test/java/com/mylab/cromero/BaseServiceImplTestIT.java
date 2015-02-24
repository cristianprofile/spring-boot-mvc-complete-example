package com.mylab.cromero;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
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
		Assert.assertEquals(baseService.findAllBases().size(), 1);
		baseService.deleteBase(base);
		Assert.assertEquals(baseService.findAllBases().size(), 0);
	}

	@Transactional
	@Test
	public void testFindAllSortedOk() {

		BaseRequest base = new BaseRequest();

		base.setName("margarita");
		baseService.saveBase(base);

		base.setName("piña");
		baseService.saveBase(base);

		base.setName("atun");
		baseService.saveBase(base);

		base.setName("codillo");
		baseService.saveBase(base);

		List<BaseResponse> findAllBasesSorted = baseService
				.findAllBasesSorted();
		Assert.assertEquals(findAllBasesSorted.size(), 4);
		Assert.assertTrue(findAllBasesSorted.get(0).getName().equals("atun"));
		Assert.assertTrue(findAllBasesSorted.get(1).getName().equals("codillo"));
		Assert.assertTrue(findAllBasesSorted.get(2).getName()
				.equals("margarita"));
		Assert.assertTrue(findAllBasesSorted.get(3).getName().equals("piña"));

	}

	@Transactional
	@Test
	public void testFindAllBasesPaginationOk() {

		BaseRequest base = new BaseRequest();

		base.setName("margarita");
		baseService.saveBase(base);

		base.setName("piña");
		baseService.saveBase(base);

		base.setName("atun");
		baseService.saveBase(base);

		base.setName("codillo");
		baseService.saveBase(base);

		List<BaseResponse> findAllBasesSorted = baseService
				.findAllBasesPagination(0);
		Assert.assertEquals(findAllBasesSorted.size(), 2);

		findAllBasesSorted = baseService.findAllBasesPagination(1);
		Assert.assertEquals(findAllBasesSorted.size(), 2);

		findAllBasesSorted = baseService.findAllBasesPagination(2);
		Assert.assertEquals(findAllBasesSorted.size(), 0);
	}

	@Transactional
	@Test
	public void testFindAllBasesPaginationAndSortOk() {

		BaseRequest base = new BaseRequest();

		base.setName("margarita");
		baseService.saveBase(base);

		base.setName("piña");
		baseService.saveBase(base);

		base.setName("atun");
		baseService.saveBase(base);

		base.setName("codillo");
		baseService.saveBase(base);

		List<BaseResponse> findAllBasesSorted = baseService
				.findAllBasesPaginationAndSorted(0);
		Assert.assertEquals(findAllBasesSorted.size(), 2);
		Assert.assertTrue(findAllBasesSorted.get(0).getName().equals("atun"));

		findAllBasesSorted = baseService.findAllBasesPaginationAndSorted(1);
		Assert.assertEquals(findAllBasesSorted.size(), 2);

		findAllBasesSorted = baseService.findAllBasesPaginationAndSorted(2);
		Assert.assertEquals(findAllBasesSorted.size(), 0);
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
		Assert.assertTrue(!findById.isPresent());

	}

}
