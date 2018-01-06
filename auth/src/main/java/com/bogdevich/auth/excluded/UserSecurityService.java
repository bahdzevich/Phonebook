package com.bogdevich.auth.security.impl;

import com.bogdevich.auth.security.IUserSecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link IUserSecurityService}
 *
 * @author Eugene Bogdevich
 * @version 1.0
 */
@Service
public class UserSecurityService implements IUserSecurityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSecurityService.class);

    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

    @Autowired
    public UserSecurityService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public String findLoggedInEmail() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if (userDetails instanceof UserDetails) {
            return ((UserDetails) userDetails).getUsername();
        }
        return null;
    }

    @Override
    public void autoLogin(String email, String password) {

    }
}
