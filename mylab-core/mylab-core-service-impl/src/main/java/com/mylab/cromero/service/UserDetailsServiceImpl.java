package com.mylab.cromero.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.mylab.cromero.domain.User;
import com.mylab.cromero.dto.UserRequest;
import com.mylab.cromero.dto.UserResponse;
import com.mylab.cromero.repository.UserRepository;
import com.mylab.cromero.service.mapper.CustomUserDetails;
import com.mylab.cromero.service.mapper.MapperSerializer;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	 @Autowired
	 private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		// TODO INCLUDE REPOSITORY SEARCH

		this.logger.debug("Begin operation: searching username :{} ", username);

		// workaround to admin user
		if ((username.equals("admin@ole.com"))) {

			
			UserResponse user = new UserResponse();
			user.setAccountNonExpired(true);
			user.setAccountNonLocked(true);
			user.setCredentialsNonExpired(true);
			user.setEnabled(true);
			user.setName("jorge");
			user.setSurname("Romero Matesanz");
			user.setUser("admin@ole.com");
			this.logger.debug("User found correct :{} ", user);
			List<String> roles = Arrays.asList("ROLE_ADMIN");
			return new CustomUserDetails(user, roles, passwordEncoder.encode("admin@ole.com"));

		}
		else
		{
		 Optional<User> findByUser = userRepository.findByUser(username);
		 if (findByUser.isPresent())
		 {
			 this.logger.debug("User found at repository :{} ", findByUser.get());
			 UserResponse userResponse = MapperSerializer.getUserToUserResponseMapperLambdaFunction().apply(findByUser.get());
			 List<String> roles = Arrays.asList(findByUser.get().getRol());
			 return new CustomUserDetails(userResponse, roles, findByUser.get().getPassword());
		 }
		 else
		 {
			 this.logger.info("User authentication error not found :{} ",username);
		     throw new UsernameNotFoundException("user login error");
			 
		 }
			
		}
	}

}
