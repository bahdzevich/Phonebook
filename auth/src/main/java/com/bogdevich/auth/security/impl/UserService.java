package com.bogdevich.auth.security.impl;

import com.bogdevich.auth.entity.domain.Role;
import com.bogdevich.auth.entity.domain.User;
import com.bogdevich.auth.repository.RoleRepository;
import com.bogdevich.auth.repository.UserRepository;
import com.bogdevich.auth.security.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
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
@Service
public class UserService implements UserDetailsService, IUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Value("${security.default-role}")
    private String DEFAULT_ROLE_NAME;

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param email of User to be logged in.
     * @return {@link org.springframework.security.core.userdetails.User}.
     * @throws UsernameNotFoundException if email not found.
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        LOGGER.info(String.format("Try to find user details, email: \'%s\'.", email));
        Optional<User> userOptional = this.findByEmail(email);
        return userOptional
                .map(user -> {
                    List<GrantedAuthority> grantedAuthorities = user
                            .getRoles().stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList());
                    return new org.springframework.security.core.userdetails.User(
                            user.getEmail(), user.getPassword(),
                            grantedAuthorities);
                }).orElseThrow(() ->
                        new UsernameNotFoundException(
                                String.format("Authentication failed: \'%s\' email not found.", email))
                );
    }

    @Override
    @Transactional
    public Optional<User> save(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        Set<Role> roles = new HashSet<>();
        User userSaved = null;
        try {
            roleRepository
                    .findRoleByName(DEFAULT_ROLE_NAME)
                    .ifPresent(roles::add);
            user.setRoles(roles);
            userSaved = userRepository.save(user);
        } catch (Exception ex) {
            LOGGER.error(String.format("Exception while saving user: \'%s\'.", user));
        }
        return Optional.ofNullable(userSaved);
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        User user = null;
        try {
            user = userRepository.findUserByEmail(email);
        } catch (Exception ex) {
            LOGGER.error(String.format("Exception while trying to find user by email: \'%s\'.", email), ex);
        }
        return Optional.ofNullable(user);
    }

    @Override
    @Transactional
    public Optional<User> findUserByID(Long id) {
        User user = null;
        try {
            user = userRepository.findOne(id);
        } catch (Exception ex) {
            LOGGER.error(String.format("Exception while trying to find user by id: \'%s\'.", id), ex);
        }
        return Optional.ofNullable(user);
    }

}
