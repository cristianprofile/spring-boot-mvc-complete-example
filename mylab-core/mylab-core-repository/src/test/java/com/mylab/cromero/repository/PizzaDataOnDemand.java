package com.mylab.cromero.repository;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.mylab.cromero.domain.Pizza;
import com.mylab.cromero.repository.PizzaRepository;

@Component
@Configurable
public class PizzaDataOnDemand {

    private Random rnd = new SecureRandom();

    private List<Pizza> data;

    @Autowired
    BaseDataOnDemand baseDataOnDemand;

    @Autowired
    EspecialidadDataOnDemand especialidadDataOnDemand;

    @Autowired
    PizzaRepository pizzaRepository;

    public Pizza getNewTransientPizza(int index) {
        Pizza obj = new Pizza();
        setName(obj, index);
        setPrice(obj, index);
        return obj;
    }

    public void setName(Pizza obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }

    public void setPrice(Pizza obj, int index) {
        Float price = new Integer(index).floatValue();
        obj.setPrice(price);
    }

    public Pizza getSpecificPizza(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Pizza obj = data.get(index);
        Long id = obj.getId();
        return pizzaRepository.findOne(id);
    }

    public Pizza getRandomPizza() {
        init();
        Pizza obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return pizzaRepository.findOne(id);
    }

    public boolean modifyPizza(Pizza obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        data = pizzaRepository.findAll(
                new org.springframework.data.domain.PageRequest(from / to, to))
                .getContent();
        if (data == null) {
            throw new IllegalStateException(
                    "Find entries implementation for 'Pizza' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }

        data = new ArrayList<Pizza>();
        for (int i = 0; i < 10; i++) {
            Pizza obj = getNewTransientPizza(i);
            try {
                pizzaRepository.save(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e
                        .getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[")
                            .append(cv.getRootBean().getClass().getName())
                            .append(".").append(cv.getPropertyPath())
                            .append(": ").append(cv.getMessage())
                            .append(" (invalid value = ")
                            .append(cv.getInvalidValue()).append(")")
                            .append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            pizzaRepository.flush();
            data.add(obj);
        }
    }
}
