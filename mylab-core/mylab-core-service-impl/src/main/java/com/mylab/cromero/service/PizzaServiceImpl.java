package com.mylab.cromero.service;

import com.mylab.cromero.repository.PizzaRepository;
import com.mylab.cromero.repository.domain.Pizza;
import com.mylab.cromero.repository.dto.PizzaRequest;
import com.mylab.cromero.repository.dto.PizzaResponse;
import com.mylab.cromero.service.mapper.LayerMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


/**
 * <h1>Base Service Implement!</h1> Bussiness Service example using repository
 * access.
 * <p>
 * <b>Base</b> Access to Pizza Example model object using repositories with
 * spring data jpa
 *
 * @author Cristian Romero Matesanz
 */

@Service
@Transactional
public class PizzaServiceImpl implements PizzaService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // Spring 4.3 use default constructor to set this bean, @Autowired is not a must now
    private PizzaRepository pizzaRepository;


    public PizzaServiceImpl(PizzaRepository pizzaRepository) {
        this.pizzaRepository = pizzaRepository;
    }


    @Override
    public List<PizzaResponse> findAllPizzas() {
        this.logger.debug("Begin operation: findAllPizzas ");
        List<Pizza> findAll = pizzaRepository.findAll();
        List<PizzaResponse> listBases = findAll.stream().map(LayerMapping.getPizzaToPizzaResponseMapperLambdaFunction())
                .collect(Collectors.toList());
        this.logger.debug("End operation: findAllPizzas {} ", listBases);
        return listBases;
    }

    @Override
    public void savePizza(final PizzaRequest pizzaRequest) {
        this.logger.debug("Begin operation: save request:{} ", pizzaRequest);

        Pizza pizza = LayerMapping.getPizzaRequestToPizzaMapperLambdaFunction().apply(pizzaRequest);
        pizzaRepository.save(pizza);
        this.logger.debug("End operation: save request:{} ", pizzaRequest);
    }


}
