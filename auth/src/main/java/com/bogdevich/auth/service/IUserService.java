package com.bogdevich.auth.service;

import com.bogdevich.auth.entity.domain.Role;
import com.bogdevich.auth.entity.domain.User;

import java.util.List;
import java.util.Optional;

/**
 * Service class for {@link com.bogdevich.auth.entity.domain.User}
 *
 * @author Eugene Bogdevich
 * @version 1.0
 */
public interface IUserService {

    /**
     * User registration.
     *
     * @param user {@link User}
     * @return {@link Optional} object that will contains an {@link User} object if it is found.
     */
    Optional<User> save(User user);

    /**
     * Find User by email.
     *
     * @param email of User.
     * @return {@link Optional} object that will contains an {@link User} object if it is found.
     */
    Optional<User> findByEmail(String email);

    /**
     * Find user by id.
     *
     * @param ID userID.
     * @return {@link Optional<User>}
     */
    Optional<User> findUserByID(Long ID);

    /**
     * Find user roles by id.
     *
     * @param ID of {@link User} object.
     * @return {@link List<Role>}
     */
    Optional<Role> findRoleByID(Long ID);
}
