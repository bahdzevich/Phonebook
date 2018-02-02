package com.bogdevich.auth.security.util;

import com.bogdevich.auth.entity.domain.User;
import com.bogdevich.auth.security.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.Optional;

/**
 * Customer identity access manager.
 *
 * @author Eugene Bogevich
 */
@Component
public class IdAccessManager {

    private final IUserService userService;

    @Autowired
    public IdAccessManager(IUserService userService) {
        this.userService = userService;
    }

    public boolean allowedToUpdate(Authentication authentication, Long id) {
        boolean allowed = false;
        if (authentication.isAuthenticated()) {
            //Principal principal = Optional.ofNullable(authentication.getPrincipal()).ifPresent();
        }
        throw new UnsupportedOperationException();
    }

    /**
     * Return user id by principal name
     * or -1L if user is not found.
     *
     * @param principal of authentication
     * @return {@link Long} id.
     */
    private Long getPrincipalId(Principal principal) {
        Long id = -1L;
        if (principal != null) {
            String email = principal.getName();
            Optional<User> userOptional = userService.findByEmail(email);
            if (userOptional.isPresent()) {
                id = userOptional
                        .get()
                        .getId();
            }
        }
        return id;
    }
}
