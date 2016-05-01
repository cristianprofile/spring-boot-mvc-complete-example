package com.mylab.cromero.repository;

import com.mylab.cromero.repository.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Access to base domain entities
 *
 * @author cristian romero
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findByUser(@Param(value = "user")  String user);


}
