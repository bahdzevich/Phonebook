package com.bogdevich.profile.security;

import com.bogdevich.profile.security.exception.PermissionException;

public interface ISecurityService {
    /**
     * @param profileId provided for checking action permission (for example: customer id)
     * @throws PermissionException if forbidden
     */
    <T> void checkPermission(T profileId);
    void checkPermission();
}
