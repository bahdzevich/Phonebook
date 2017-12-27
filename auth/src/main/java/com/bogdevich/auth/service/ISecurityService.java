package com.bogdevich.auth.service;

/**
 * Service for security.
 *
 * @author Eugene Bogdevich
 * @version 1.0
 */
public interface ISecurityService {

    /**
     * Find logged in users.
     * @return User email.
     */
    String findLoggedInEmail();

    /**
     * Auto log in.
     * @param email of User.
     * @param password of User.
     */
    void autoLogin(String email, String password);
}
