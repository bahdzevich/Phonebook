package com.bogdevich.auth.security.impl;

import com.bogdevich.auth.entity.domain.User;
import com.bogdevich.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Implementation of {@link UserDetailsService}
 *
 * @author Eugene Bogdevich
 * @version 1.0
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * @param email of User to be logged in.
     * @return {@link org.springframework.security.core.userdetails.User}.
     * @throws UsernameNotFoundException if email not found.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.debug(String.format("Load user by user name: \"%s\"", email));
        Optional<User> userOptional = userRepository.findUserByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            logger.debug(String.format("Load user by user name: %s", user));
            List<GrantedAuthority> grantedAuthorities = user
                    .getRoles()
                    .stream()
                    .map(role -> new SimpleGrantedAuthority(role.getName()))
                    .collect(Collectors.toList());
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);
        } else {
            logger.debug("Load user by user name: user not found");
            throw new UsernameNotFoundException(String.format("No user exists for \"%s\".", email));
        }
    }
}
