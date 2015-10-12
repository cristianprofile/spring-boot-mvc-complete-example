package com.mylab.cromero.cromero.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mylab.cromero.cromero.domain.Especialidad;

@Repository
public interface EspecialidadRepository extends
        JpaRepository<Especialidad, Long> {
}
