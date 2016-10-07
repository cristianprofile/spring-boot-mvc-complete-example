package com.mylab.cromero.service;

import com.mylab.cromero.repository.dto.BaseRequest;
import com.mylab.cromero.repository.dto.BaseResponse;
import com.mylab.cromero.repository.exception.BaseNotFoundException;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;


@RunWith(SpringRunner.class)
@SpringBootTest
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
        MatcherAssert.assertThat(baseService.findAllBases(), hasSize(1));
        baseService.deleteBase(base);
        MatcherAssert.assertThat(baseService.findAllBases(), empty());
    }

    @Transactional
    @Test
    public void testFindAllSortedOk() {

        HashMap<String, String> pizzas = new HashMap<String, String>();

        pizzas.put("atun", "atun");
        pizzas.put("margarita", "margarita");
        pizzas.put("pinya", "piña");
        pizzas.put("codillo", "codillo");

        pizzas.forEach((key, value) -> {
                    BaseRequest base = new BaseRequest();
                    base.setName(value);
                    baseService.saveBase(base);
                }

        );

        List<BaseResponse> findAllBasesSorted = baseService
                .findAllBasesSorted();

        assertThat(findAllBasesSorted, hasSize(4));

        // test if all values is in my collection and if order is correct

        List<String> collectRequest = mapOfString(findAllBasesSorted);

        assertThat(collectRequest, contains(pizzas.get("atun"), pizzas.get("codillo"),
                pizzas.get("margarita"), pizzas.get("pinya")));

    }

    @Transactional
    @Test
    public void testFindAllBasesPaginationAndSortOk() {

        HashMap<String, String> pizzas = new HashMap<String, String>();

        pizzas.put("atun", "atun");
        pizzas.put("margarita", "margarita");
        pizzas.put("pinya", "piña");
        pizzas.put("codillo", "codillo");

        pizzas.forEach((key, value) -> {
                    BaseRequest base = new BaseRequest();
                    base.setName(value);
                    baseService.saveBase(base);
                }

        );

        List<BaseResponse> findAllBasesSorted = baseService
                .findAllBasesPaginationAndSorted(0);

        List<String> collectRequest = mapOfString(findAllBasesSorted);


        assertThat(collectRequest, hasSize(2));
        assertThat(collectRequest, contains(pizzas.get("atun"), pizzas.get("codillo")));

        findAllBasesSorted = baseService.findAllBasesPaginationAndSorted(1);

        collectRequest = mapOfString(findAllBasesSorted);


        assertThat(collectRequest, hasSize(2));
        assertThat(collectRequest, contains(pizzas.get("margarita"), pizzas.get("pinya")));

        findAllBasesSorted = baseService.findAllBasesPaginationAndSorted(2);
        assertThat(findAllBasesSorted, hasSize(0));
    }


    @Transactional
    @Test
    public void testFindAllOptional() {
        BaseRequest base = new BaseRequest();
        base.setName("margarita");
        base.setId(130L);
        baseService.saveBase(base);
        Optional<BaseResponse> findById = baseService.findById(130L);
        assertThat(findById.isPresent(), equalTo(false));

    }


    private List<String> mapOfString(List<BaseResponse> findAllBasesSorted) {
        List<String> collectRequest = findAllBasesSorted.stream()
                .map(baseResponse -> {
                    return baseResponse.getName();
                }).collect(Collectors.toList());
        return collectRequest;
    }

}
