package com.bogdevich.auth.controller;

import com.bogdevich.auth.entity.domain.User;
import com.bogdevich.auth.entity.dto.UserRequestDto;
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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * Controller provides with user api.
 *
 * @author Eugene Bogdevich
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
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
    public ResponseEntity<Principal> getPrincipal(Principal principal) {
        return Optional.ofNullable(principal)
                .map(principal1 -> new ResponseEntity<>(principal1, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
    }

    @GetMapping(value = "/current/info")
    @SuppressWarnings("unchecked")
    public ResponseEntity<Map<String, String>> getPrincipalInfo(Principal principal) {
        return (ResponseEntity<Map<String, String>>) Optional.ofNullable(principal)
                .map(principal1 -> userService
                        .findByEmail(principal.getName())
                        .map(user -> {
                            Map<String, String> hashMap = new HashMap<>();
                            hashMap.put(PRINCIPAL_ID_HEADER, String.valueOf(user.getId()));
                            hashMap.put(PRINCIPAL_NAME_HEADER, user.getEmail());
                            return new ResponseEntity(hashMap, HttpStatus.OK); })
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND)))
                .orElse(new ResponseEntity<Map<String, String>>(HttpStatus.UNAUTHORIZED));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> createUser(
            @RequestBody @Valid UserRequestDto userRequestDto) {
        return userService
                .save(dtoToUser(userRequestDto))
                .map(user -> new ResponseEntity<>("Success", HttpStatus.CREATED))
                .orElse(new ResponseEntity<>("Already exists", HttpStatus.BAD_REQUEST));
    }

    private User dtoToUser(UserRequestDto requestDto) {
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(requestDto.getPassword());
        user.setName(requestDto.getName());
        user.setLastname(requestDto.getLastname());
        user.setPhone(requestDto.getPhone());
        user.setSkype(requestDto.getSkype());
        user.setRoom(requestDto.getRoom());
        return user;
    }
}
