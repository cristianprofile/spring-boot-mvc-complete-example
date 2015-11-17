package com.mylab.cromero.service.mapper;

import com.mylab.cromero.repository.domain.Base;
import com.mylab.cromero.repository.domain.Pizza;
import com.mylab.cromero.repository.dto.BaseResponse;
import com.mylab.cromero.repository.dto.PizzaRequest;
import com.mylab.cromero.repository.dto.PizzaResponse;

import java.util.function.Function;

public class LayerMapping {


    public static Function<Base, ? extends BaseResponse> getBaseToBaseResponseMapperLambdaFunction() {
        return base -> {
            BaseResponse baseResponse = new BaseResponse();
            baseResponse.setName(base.getName());
            baseResponse.setId(base.getId());
            return baseResponse;
        };
    }


    public static Function<Pizza, ? extends PizzaResponse> getPizzaToPizzaResponseMapperLambdaFunction() {
        return pizza -> {
            PizzaResponse pizzaResponse = new PizzaResponse();
            pizzaResponse.setName(pizza.getName());
            pizzaResponse.setPrice(pizza.getPrice());
            return pizzaResponse;
        };
    }

    public static Function<PizzaRequest, ? extends Pizza> getPizzaRequestToPizzaMapperLambdaFunction() {
        return pizzaRequest -> {
            Pizza pizza = new Pizza();
            pizza.setName(pizzaRequest.getName());
            pizza.setPrice(pizzaRequest.getPrice());
            return pizza;
        };
    }


}
