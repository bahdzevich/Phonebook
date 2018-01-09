package com.bogdevich.auth.controller;

import com.bogdevich.auth.controller.exception.NotFoundException;
import com.bogdevich.auth.entity.domain.Role;
import com.bogdevich.auth.entity.domain.User;
import com.bogdevich.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * Controller provides with user api.
 *
 * @author Eugene Bogdevich
 * @version 1.0
 */
@RestController
@RequestMapping("/phonebook/v1/users")
public class UserController {

    private IUserService userService;

    @Autowired
    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.GET,
            produces = {"application/json"}
    )
    @PreAuthorize("hasAuthority('ADMIN')")
    @ResponseBody
    public ResponseEntity<User> getUserByID(@PathVariable("id") Long id) throws NotFoundException{
        User user = userService
                .findUserByID(id)
                .orElseThrow(() -> new NotFoundException("User not found"));
        return ResponseEntity.ok(user);
    }

    @RequestMapping(
            value = "/roles/{id}",
            method = RequestMethod.GET,
            produces = {"application/json"}
    )
    @ResponseBody
    public ResponseEntity<Role> getRoleByID(@PathVariable("id") Long id) throws NotFoundException{
        Role role = userService
                .findRoleByID(id)
                .orElseThrow(() -> new NotFoundException("Role not found"));
        return ResponseEntity.ok(role);
    }
}
