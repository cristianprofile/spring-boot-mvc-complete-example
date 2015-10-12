package com.mylab.cromero.cromero.repository;

import java.util.List;

import com.mylab.cromero.cromero.domain.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    List<Pizza> findByNameContainingAndPriceLessThan(String name, Float price);

    List<Pizza> findByNameIgnoreCase(String name);

    List<Pizza> findByNameIgnoreCaseContaining(String name);

    List<Pizza> findByEspecialidadName(String name);

    List<Pizza> findByToppingsName(String name);
    
}
