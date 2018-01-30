package com.bogdevich.auth.controller;

import com.bogdevich.auth.controller.exception.DataNotFoundException;
import com.bogdevich.auth.entity.domain.Role;
import com.bogdevich.auth.entity.domain.User;
import com.bogdevich.auth.security.IUserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * Controller provides with user api.
 *
 * @author Eugene Bogdevich
 * @version 1.0
 */
@RestController
@RequestMapping("/users")
@PropertySource("classpath:security.properties")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Value("${security.header.principal-id}")
    private String PRINCIPAL_ID_HEADER;

    @Value("${security.header.principal-name}")
    private String PRINCIPAL_NAME_HEADER;

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/current")
    public Principal getPrincipal(Principal principal, HttpServletRequest request) {
        LOGGER.info(String.format("%s", request));
        return principal;
    }

    @GetMapping(value = "/current/info")
    public ResponseEntity<Map<String, String>> getPrincipalInfo(Principal principal) {
        Map<String, String> principalInfo = userService
                .findByEmail(principal.getName())
                .map(user -> {
                    Map<String, String> hashMap = new HashMap<>();
                    hashMap.put(PRINCIPAL_ID_HEADER, String.valueOf(user.getId()));
                    hashMap.put(PRINCIPAL_NAME_HEADER, user.getEmail());
                    return hashMap;
                }).orElseThrow(() -> new DataNotFoundException("Authentication failed."));
        return new ResponseEntity<>(principalInfo, HttpStatus.OK);
    }
}
