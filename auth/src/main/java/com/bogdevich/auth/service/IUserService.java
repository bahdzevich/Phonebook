package com.bogdevich.auth.service;

import com.bogdevich.auth.model.User;

import java.util.Optional;

/**
 * Service class for {@link com.bogdevich.auth.model.User}
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
}
