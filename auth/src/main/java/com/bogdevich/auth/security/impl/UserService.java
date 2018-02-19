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
import org.springframework.data.domain.Example;
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
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (email == null) {
            throw new IllegalArgumentException("Email is null");
        }
        return Optional
                .ofNullable(userRepository.findUserByEmail(email))
                .map(user -> {
                    List<GrantedAuthority> authorities = user.getRoles()
                            .stream()
                            .map(role -> new SimpleGrantedAuthority(role.getName()))
                            .collect(Collectors.toList());
                    return new org.springframework.security.core.userdetails.
                            User(user.getEmail(), user.getPassword(), authorities); })
                .orElseThrow(() ->
                        new UsernameNotFoundException("Email is not found: " + email));
    }

    @Override
    @Transactional
    public Optional<User> save(final User user) {
        if (user == null) {
            throw new IllegalArgumentException("User is null");
        }
        User savedUser = null;
        if (!userRepository.exists(Example.of(user))) {
            Role role = roleRepository.findRoleByName(DEFAULT_ROLE_NAME);
            Set<Role> roles = new HashSet<>();
            roles.add(role);
            savedUser = userRepository.save(user);
            savedUser.setPassword(passwordEncoder.encode(user.getPassword()));
            savedUser.setRoles(roles);
        }
        return Optional.ofNullable(savedUser);
    }

    @Override
    @Transactional
    public Optional<User> findByEmail(final String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email is null");
        }
        return Optional.ofNullable(userRepository.findUserByEmail(email));
    }

    @Override
    @Transactional
    public Optional<User> findUserByID(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("User id is null");
        }
        return Optional.ofNullable(userRepository.findOne(id));
    }

}
