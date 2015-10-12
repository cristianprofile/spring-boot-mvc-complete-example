package com.mylab.cromero.repository;

import com.mylab.cromero.repository.domain.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    
    Optional<Base> findById(Long id);
    
   
    

}
