package com.mylab.cromero.repository;

import com.mylab.cromero.repository.domain.Especialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EspecialidadRepository extends
        JpaRepository<Especialidad, Long> {
}
