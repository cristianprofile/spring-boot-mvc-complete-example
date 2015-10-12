package com.mylab.cromero.cromero.repository;

import java.util.Optional;

import com.mylab.cromero.cromero.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Access to base domain entities
 * @author cristian romero
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    
	Optional<User> findByUser(String user);
    

}
