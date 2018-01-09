package com.bogdevich.profile.controller.advice;

import com.bogdevich.profile.controller.ProfileController;
import com.bogdevich.profile.controller.ProjectController;
import com.bogdevich.profile.controller.RoleController;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 */
@ControllerAdvice(
        basePackageClasses = {
                ProfileController.class,
                ProjectController.class,
                RoleController.class
        })
public class ProfileControllerAdvice extends ResponseEntityExceptionHandler{
}
