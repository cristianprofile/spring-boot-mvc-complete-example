package com.mylab.cromero.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mylab.cromero.domain.Pizza;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    List<Pizza> findByNameContainingAndPriceLessThan(String name, Float price);

    List<Pizza> findByNameIgnoreCase(String name);

    List<Pizza> findByNameIgnoreCaseContaining(String name);

    List<Pizza> findByEspecialidadName(String name);

    List<Pizza> findByToppingsName(String name);
    
}
