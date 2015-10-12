package com.mylab.cromero.cromero.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mylab.cromero.cromero.domain.Base;

/**
 * Access to base domain entities
 * @author cristian romero
 *
 */
@Repository
public interface BaseRepository extends JpaRepository<Base, Long> {
    /**
     * Find base entity by its name
     * @param name value of base entity name
     * @return base entity
     */
    List<Base> findByName(String name);
    
    Optional<Base> findById(Long id );
    
   
    

}
