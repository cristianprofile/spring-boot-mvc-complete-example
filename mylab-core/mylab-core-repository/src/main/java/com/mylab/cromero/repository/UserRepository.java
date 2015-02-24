package com.mylab.cromero.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mylab.cromero.domain.User;

/**
 * Access to base domain entities
 * @author cristian romero
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    
	Optional<User> findByUser(String user);
    

}
