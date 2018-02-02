package com.bogdevich.gateway.filter;


import com.bogdevich.gateway.client.AuthServiceClient;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@PropertySource("classpath:filter.properties")
public class AddPrincipalHeaderFilter extends ZuulFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddPrincipalHeaderFilter.class);

    @Value("${principal-header-filter.header.principal-id}")
    private String principalIdHeader;
    @Value("${principal-header-filter.header.principal-name}")
    private String principalNameHeader;
    @Value("${principal-header-filter.filter-order}")
    private int filterOrder;
    @Value("${principal-header-filter.filter-type}")
    private String filterType;
    @Value("${principal-header-filter.should-filter}")
    private boolean shouldFilter;
    @Value("${principal-header-filter.header.auth}")
    private String authTokenHeader;
    @Value("${principal-header-filter.header.principal-authorities}")
    private String authoritiesHeader;

    private AuthServiceClient serviceClient;

    @Autowired
    public AddPrincipalHeaderFilter(AuthServiceClient serviceClient) {
        super();
        this.serviceClient = serviceClient;
    }

    @Override
    public String filterType() {
        return filterType;
    }

    @Override
    public int filterOrder() {
        return filterOrder;
    }

    @Override
    public boolean shouldFilter() {
        return shouldFilter;
    }

    @Override
    public Object run() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getHeader(authTokenHeader);
        LOGGER.info(String.format("Request :: %s %s %s", request.getMethod(), request.getRequestURI(), request.getProtocol()));
        LOGGER.info(String.format("Token :: %s", token));
        LOGGER.info(String.format("Authorities :: %s", SecurityContextHolder.getContext().getAuthentication().getAuthorities()));
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Map<String, String> principalInfoMap = this.getPrincipalInfoMap(token);
        if (principalInfoMap != null && authorities != null) {
            principalInfoMap.forEach(requestContext::addZuulRequestHeader);
            requestContext.addZuulRequestHeader(
                    authoritiesHeader,
                    String.join(",", authorities
                            .stream().map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList())));
        }
        return null;
    }

    private Map<String, String> getPrincipalInfoMap(String token) {
        Map<String, String> principalInfoMap = null;
        try{
            principalInfoMap = serviceClient.getPrincipalInfo(token).getBody();
        } catch (Exception ex) {
            LOGGER.error("Exception while trying to load principal info.", ex);
        }
        return principalInfoMap;
    }
}
