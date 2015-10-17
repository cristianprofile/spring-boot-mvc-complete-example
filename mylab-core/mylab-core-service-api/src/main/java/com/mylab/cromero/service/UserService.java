package com.mylab.cromero.service;

import com.mylab.cromero.repository.dto.UserRequest;
import com.mylab.cromero.repository.dto.UserResponse;

import java.util.List;

/**
 * <h1>UserService</h1>
 * UserService interface definition
 * <p>
 * <b>User Service</b> definition of methods of interface
 * for sevice layer
 *
 * @author Cristian Romero Matesanz
 */
public interface UserService {

    public List<UserResponse> findAllUsers();

    public void saveUser(UserRequest userRequest);


}
