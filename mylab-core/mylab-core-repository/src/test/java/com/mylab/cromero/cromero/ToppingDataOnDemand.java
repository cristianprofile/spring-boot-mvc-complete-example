package com.mylab.cromero.cromero;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import com.mylab.cromero.cromero.domain.Topping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import com.mylab.cromero.cromero.repository.ToppingRepository;

@Component
@Configurable
public class ToppingDataOnDemand {

    private Random rnd = new SecureRandom();

    private List<Topping> data;

    @Autowired
    ToppingRepository toppingRepository;

    public Topping getNewTransientTopping(int index) {
        Topping obj = new Topping();
        setName(obj, index);
        return obj;
    }

    public void setName(Topping obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }

    public Topping getSpecificTopping(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Topping obj = data.get(index);
        Long id = obj.getId();
        return toppingRepository.findOne(id);
    }

    public Topping getRandomTopping() {
        init();
        Topping obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return toppingRepository.findOne(id);
    }

    public boolean modifyTopping(Topping obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        data = toppingRepository.findAll(
                new org.springframework.data.domain.PageRequest(from / to, to))
                .getContent();
        if (data == null) {
            throw new IllegalStateException(
                    "Find entries implementation for 'Topping' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }

        data = new ArrayList<Topping>();
        for (int i = 0; i < 10; i++) {
            Topping obj = getNewTransientTopping(i);
            try {
                toppingRepository.save(obj);
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
            toppingRepository.flush();
            data.add(obj);
        }
    }
}
