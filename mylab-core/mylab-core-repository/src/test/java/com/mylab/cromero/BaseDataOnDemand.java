package com.mylab.cromero;

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

import com.mylab.cromero.domain.Base;
import com.mylab.cromero.repository.BaseRepository;

@Component
@Configurable
public class BaseDataOnDemand {

    private Random rnd = new SecureRandom();

    private List<Base> data;

    @Autowired
    BaseRepository baseRepository;

    public Base getNewTransientBase(int index) {
        Base obj = new Base();
        setName(obj, index);
        return obj;
    }

    public void setName(Base obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }

    public Base getSpecificBase(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Base obj = data.get(index);
        Long id = obj.getId();
        return baseRepository.findOne(id);
    }

    public Base getRandomBase() {
        init();
        Base obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return baseRepository.findOne(id);
    }

    public boolean modifyBase(Base obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        data = baseRepository.findAll(
                new org.springframework.data.domain.PageRequest(from / to, to))
                .getContent();
        if (data == null) {
            throw new IllegalStateException(
                    "Find entries implementation for 'Base' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }

        data = new ArrayList<Base>();
        for (int i = 0; i < 10; i++) {
            Base obj = getNewTransientBase(i);
            try {
                baseRepository.save(obj);
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
            baseRepository.flush();
            data.add(obj);
        }
    }
}
