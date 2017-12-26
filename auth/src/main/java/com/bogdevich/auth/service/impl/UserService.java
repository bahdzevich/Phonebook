package com.bogdevich.auth.service.impl;

import com.bogdevich.auth.model.Role;
import com.bogdevich.auth.model.User;
import com.bogdevich.auth.repository.RoleRepository;
import com.bogdevich.auth.repository.UserRepository;
import com.bogdevich.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * Implementation of {@link IUserService}.
 *
 * @author Eugene Bogdevich
 * @version 1.0
 */
@Service
public class UserService implements IUserService {

    private final String DEFAULT_ROLE_NAME = "EMPLOYEE";

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Save new {@link User}.
     * @param user {@link User} object to be saved.
     * @return {@link Optional} contains {@link User} object.
     */
    @Override
    public Optional<User> save(User user) {
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        Set<Role> roles = new HashSet<>();
        roleRepository
                .findRoleByName(DEFAULT_ROLE_NAME)
                .ifPresent(roles::add);
        user.setRoles(roles);
        return Optional.ofNullable(userRepository.save(user));
    }

    /**
     * Find {@link User} by email.
     * @param email of User.
     * @return {@link Optional}
     */
    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }
}
