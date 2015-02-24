package com.mylab.cromero.service;

import java.util.List;

import com.mylab.cromero.dto.PizzaRequest;
import com.mylab.cromero.dto.PizzaResponse;

/**
* <h1>BaseService</h1>
* BaseService interface definition 
* <p>
* <b>BaseService</b> definition of methods of interface
* for sevice layer
*
* @author  Cristian Romero Matesanz
*
* 
*/
public interface PizzaService {

    

    /**
     * list all pizzas from repository
     * @return
     */
    List<PizzaResponse> findAllPizzas();

    /**
     * save a pizza from repository
     * @param base base to save
     */
    void savePizza(final PizzaRequest pizzaRequest);
    
    /**
     * get a base of pizza from repository
     * @param id base to load
     * @return 
     */

}
