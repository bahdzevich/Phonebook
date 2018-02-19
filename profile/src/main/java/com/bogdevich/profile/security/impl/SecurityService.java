package com.bogdevich.profile.security.impl;

import com.bogdevich.profile.security.ISecurityService;
import com.bogdevich.profile.security.SecurityContextHolder;
import com.bogdevich.profile.security.exception.PermissionException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:security.properties")
public class SecurityService implements ISecurityService{

    @Value("${role.admin-role}")
    protected String adminRoleName;

    @Override
    public <T> void checkPermission(final T profileId) {
        boolean permitted = SecurityContextHolder
                .getThreadLocalAuthorityList().stream()
                .anyMatch(s -> s.equals(adminRoleName))
                || SecurityContextHolder.getThreadLocalPrincipalId()
                .equals(profileId);
        if (!permitted) {
            throw new PermissionException("Access denied");
        }
    }

    @Override
    public void checkPermission() {
        boolean permitted = SecurityContextHolder
                .getThreadLocalAuthorityList()
                .stream().anyMatch(s -> s.equals(adminRoleName));
        if (!permitted) {
            throw new PermissionException("Access denied");
        }
    }
}
