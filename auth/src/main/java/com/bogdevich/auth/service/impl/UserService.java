package com.bogdevich.auth.service.impl;

import com.bogdevich.auth.entity.domain.Role;
import com.bogdevich.auth.entity.domain.User;
import com.bogdevich.auth.repository.RoleRepository;
import com.bogdevich.auth.repository.UserRepository;
import com.bogdevich.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
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

    @Override
    @Transactional
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    @Transactional
    public Optional<User> findUserByID(Long ID) {
        return Optional.ofNullable(userRepository.findOne(ID));
    }

    @Override
    public Optional<Role> findRoleByID(Long ID) {
        return Optional.ofNullable(roleRepository.findOne(ID));
    }
}
