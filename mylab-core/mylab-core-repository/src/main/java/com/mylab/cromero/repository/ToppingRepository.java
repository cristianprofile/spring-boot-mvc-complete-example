package com.mylab.cromero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mylab.cromero.domain.Topping;

@Repository
public interface ToppingRepository extends JpaRepository<Topping, Long> {
}
