package com.mylab.cromero.service;

import java.util.List;
import java.util.Optional;

import com.mylab.cromero.dto.BaseRequest;
import com.mylab.cromero.dto.BaseResponse;
import com.mylab.cromero.dto.UserRequest;
import com.mylab.cromero.dto.UserResponse;
import com.mylab.cromero.exception.BaseNotFoundException;

/**
* <h1>UserService</h1>
* UserService interface definition 
* <p>
* <b>User Service</b> definition of methods of interface
* for sevice layer
*
* @author  Cristian Romero Matesanz
*
* 
*/
public interface UserService {

	public List<UserResponse> findAllUsers();

    public void saveUser(UserRequest userRequest);

    

}
