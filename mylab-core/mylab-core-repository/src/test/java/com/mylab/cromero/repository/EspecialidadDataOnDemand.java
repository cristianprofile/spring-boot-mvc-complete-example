package com.mylab.cromero.repository;

import com.mylab.cromero.repository.domain.Especialidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Configurable
@Component
public class EspecialidadDataOnDemand {

    @Autowired
    EspecialidadRepository especialidadRepository;
    private Random rnd = new SecureRandom();
    private List<Especialidad> data;

    public Especialidad getNewTransientEspecialidad(int index) {
        Especialidad obj = new Especialidad();
        setActive(obj, index);
        setName(obj, index);
        return obj;
    }

    public void setActive(Especialidad obj, int index) {
        Boolean active = Boolean.TRUE;
        obj.setActive(active);
    }

    public void setName(Especialidad obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }

    public Especialidad getSpecificEspecialidad(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Especialidad obj = data.get(index);
        Long id = obj.getId();
        return especialidadRepository.findOne(id);
    }

    public Especialidad getRandomEspecialidad() {
        init();
        Especialidad obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return especialidadRepository.findOne(id);
    }

    public boolean modifyEspecialidad(Especialidad obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        data = especialidadRepository.findAll(
                new org.springframework.data.domain.PageRequest(from / to, to))
                .getContent();
        if (data == null) {
            throw new IllegalStateException(
                    "Find entries implementation for 'Especialidad' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }

        data = new ArrayList<Especialidad>();
        for (int i = 0; i < 10; i++) {
            Especialidad obj = getNewTransientEspecialidad(i);
            try {
                especialidadRepository.save(obj);
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e
                        .getConstraintViolations().iterator(); iter.hasNext(); ) {
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
            especialidadRepository.flush();
            data.add(obj);
        }
    }
}
