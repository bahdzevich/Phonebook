package com.bogdevich.auth.service.util;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Component provides access to BCryptPasswordEncoder bean.
 *
 * @author Eugene Bogdevich
 * @version 1.0
 */
@Component
public class PasswordEncoderUtil {

    private static final int WORKLOAD = 12;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(WORKLOAD);
    }
}
