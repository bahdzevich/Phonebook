package com.bogdevich.auth.service.impl;

import com.bogdevich.auth.service.ISecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link ISecurityService}
 *
 * @author Eugene Bogdevich
 * @version 1.0
 */
@Service
public class SecurityService implements ISecurityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityService.class);

    @Autowired
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;

//    @Autowired
//    public SecurityService(AuthenticationManager authenticationManager, UserDetailsService userDetailsService) {
//        this.authenticationManager = authenticationManager;
//        this.userDetailsService = userDetailsService;
//    }

    @Override
    public String findLoggedInEmail() {
        return null;
    }

    @Override
    public void autoLogin(String email, String password) {

    }
}
