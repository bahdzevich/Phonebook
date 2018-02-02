package com.bogdevich.auth.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class PrincipalHeaderFilter implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(PrincipalHeaderFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        LOGGER.info(String.format(
                "REQUEST :: %s %s %s",
                req.getMethod(),
                req.getRequestURI(),
                Collections
                        .list(req.getHeaderNames())
                        .stream()
                        .collect(Collectors.toMap(s -> s, req::getHeader))));
        LOGGER.info(String.format(
                "RESPONSE :: %s %s",
                resp.getStatus(),
                resp.getHeaderNames()
                        .stream()
                        .collect(Collectors.toMap(s -> s, req::getHeader))));

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
