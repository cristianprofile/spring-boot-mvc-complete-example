package com.mylab.cromero.service.security;

import com.mylab.cromero.repository.UserRepository;
import com.mylab.cromero.repository.domain.User;
import com.mylab.cromero.repository.dto.UserResponse;
import com.mylab.cromero.service.mapper.MapperSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

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
            List<String> roles = Collections.singletonList("ROLE_ADMIN");
            return new CustomUserDetails(user, roles, passwordEncoder.encode("admin@ole.com"));

        }
        else {
            Optional<User> findByUser = userRepository.findByUser(username);
            if (findByUser.isPresent()) {
                this.logger.debug("User found at repository :{} ", findByUser.get());
                UserResponse userResponse = MapperSerializer.getUserToUserResponseMapperLambdaFunction().apply(findByUser.get());
                List<String> roles = Collections.singletonList(findByUser.get().getRol());
                return new CustomUserDetails(userResponse, roles, findByUser.get().getPassword());
            }
            else {
                this.logger.info("User authentication error not found :{} ", username);
                throw new UsernameNotFoundException("user login error");

            }

        }
    }


}
