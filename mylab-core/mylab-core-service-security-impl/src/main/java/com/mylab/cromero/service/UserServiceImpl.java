package com.mylab.cromero.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mylab.cromero.domain.User;
import com.mylab.cromero.dto.UserRequest;
import com.mylab.cromero.dto.UserResponse;
import com.mylab.cromero.repository.UserRepository;
import com.mylab.cromero.service.mapper.MapperSerializer;

/**
 * <h1>Base Service Implement!</h1> Bussiness Service example using repository
 * access.
 * <p>
 * <b>Base</b> Access to Base Pizza Example model object using repositories with
 * spring data jpa
 *
 * @author Cristian Romero Matesanz
 *
 * 
 */

@Service
@Transactional
public class UserServiceImpl implements UserService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<UserResponse> findAllUsers() {
		this.logger.debug("Begin operation: findUsers ");

		List<User> findAll = userRepository.findAll();
		List<UserResponse> listUsers = findAll
				.stream()
				.map(MapperSerializer
						.getUserToUserResponseMapperLambdaFunction())
				.collect(Collectors.toList());

		this.logger.debug("End operation: findAllUsers {} ", listUsers);
		return listUsers;
	}

	@Override
	public void saveUser(final UserRequest userRequest) {
		this.logger.debug("Begin operation: save request:{} ", userRequest);
		User user = MapperSerializer.getUserRequestToUserMapperLambdaFunction()
				.apply(userRequest);
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		this.logger.debug("End operation: save request:{} ", user);
	}

}
