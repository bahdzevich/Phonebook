package com.bogdevich.auth.service.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Proxy password encoder.
 *
 * @author Eugene Bogdevich
 * @version 1.0
 */
@Component
public class PhonebookPasswordEncoder implements PasswordEncoder {

    /**
     * Define the BCrypt WORKLOAD to use when generating password hashes. Minimum 4, maximum 31.
     */
    private final int WORKLOAD = 12;
    private final PasswordEncoder passwordEncoder;

    public PhonebookPasswordEncoder() {
        this.passwordEncoder = new BCryptPasswordEncoder(WORKLOAD);
    }

    @Override
    public String encode(CharSequence charSequence) {
        return passwordEncoder.encode(charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return passwordEncoder.matches(charSequence, s);
    }
}
