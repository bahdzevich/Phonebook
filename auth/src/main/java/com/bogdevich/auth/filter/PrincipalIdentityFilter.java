package com.bogdevich.auth.filter;

import com.bogdevich.auth.security.IUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Provides request with identification of authenticated user.
 *
 * @author Eugene Bogdevich
 */

public class PrincipalIdentityFilter extends GenericFilterBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrincipalIdentityFilter.class);
    private final IUserService userService;

    @Autowired
    public PrincipalIdentityFilter(IUserService userService) {
        super();
        this.userService = userService;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }
}
