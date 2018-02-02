package com.bogdevich.profile.filter;

import com.bogdevich.profile.context.SecurityContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

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
@PropertySource("classpath:security.properties")
public class PrincipalHeaderFilter implements Filter {

    private final static Logger LOGGER = LoggerFactory.getLogger(PrincipalHeaderFilter.class);

    @Value("${principal-header-filter.header.principal-id}")
    private String principalIdHeader;
    @Value("${principal-header-filter.header.principal-name}")
    private String principalNameHeader;
    @Value("${principal-header-filter.header.principal-authorities}")
    private String principalAuthoritiesHeader;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Set {@link ThreadLocal} variables to {@link SecurityContextHolder}
     *
     * @author Eugene Bogdevich
     */
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

        try {
            Long principalId = Long.parseLong(req.getHeader(this.principalIdHeader));
            String principalName = Objects.requireNonNull(req.getHeader(this.principalNameHeader));
            List<String> principalAuthorityList = Optional
                    .ofNullable(req.getHeader(principalAuthoritiesHeader))
                    .map(s -> Arrays
                            .stream(s.split(","))
                            .collect(Collectors.toList()))
                    .orElse(Collections.emptyList());

            LOGGER.info(String.format(
                    "Setting security context; id:\'%s\', name:\'%s\', authorities:\'%s\'",
                    principalId,
                    principalName,
                    principalAuthorityList));

            SecurityContextHolder.setThreadLocalPrincipalId(principalId);
            SecurityContextHolder.setThreadLocalPrincipalName(principalName);
            SecurityContextHolder.setThreadLocalAuthorityList(principalAuthorityList);
        } catch (Exception ex) {
            LOGGER.error("Unable to set security context.", ex);
            resp.sendError(HttpServletResponse.SC_FORBIDDEN, ex.getMessage());
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
