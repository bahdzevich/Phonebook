package com.bogdevich.auth.security;

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
     * Create new {@link User}.
     *
     * @param user {@link User}
     * @return {@link User} saved instance
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
}
