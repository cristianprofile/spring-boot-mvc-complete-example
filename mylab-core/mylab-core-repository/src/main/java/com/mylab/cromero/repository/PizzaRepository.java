package com.mylab.cromero.repository;

import com.mylab.cromero.repository.domain.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {

    List<Pizza> findByNameContainingAndPriceLessThan(@Param(value = "name") String name, @Param(value = "price") Float price);

    List<Pizza> findByNameIgnoreCase(@Param(value = "name") String name);

    List<Pizza> findByNameIgnoreCaseContaining(@Param(value = "name")String name);

    List<Pizza> findByEspecialidadName(@Param(value = "name") String name);

    List<Pizza> findByToppingsName(@Param(value = "name") String name);

}
