package com.bogdevich.profile.controller.advice;

import com.bogdevich.profile.controller.ProfileController;
import com.bogdevich.profile.controller.ProjectController;
import com.bogdevich.profile.controller.RoleController;
import com.bogdevich.profile.controller.exception.DataNotFoundException;
import com.bogdevich.profile.controller.exception.InternalServiceException;
import com.bogdevich.profile.entity.dto.response.ErrorDTO;
import com.bogdevich.profile.entity.dto.response.FieldErrorDTO;
import com.bogdevich.profile.entity.dto.response.MessageDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global exception handler.
 *
 * @author Eugene Bogdevich
 */
@ControllerAdvice(
        basePackageClasses = {
                ProfileController.class,
                ProjectController.class,
                RoleController.class
        })
public class ProfileControllerAdvice extends ResponseEntityExceptionHandler{

        private Logger logger = LoggerFactory.getLogger(this.getClass());

        @ExceptionHandler(DataNotFoundException.class)
        public ResponseEntity<MessageDTO> handleNotFoundException(Throwable ex){
                MessageDTO messageDTO = new MessageDTO();
                messageDTO.setTime(LocalDateTime.now().toString());
                messageDTO.setStatus(HttpStatus.NOT_FOUND.value());
                messageDTO.setMessage(ex.getMessage());
                return new ResponseEntity<>(messageDTO, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler(InternalServiceException.class)
        public ResponseEntity<MessageDTO> handleInternalServiceException(Throwable ex, HttpServletRequest httpServletRequest) {
                MessageDTO messageDTO = new MessageDTO();
                messageDTO.setTime(LocalDateTime.now().toString());
                messageDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                messageDTO.setMessage(ex.getMessage());
                messageDTO.setPath(httpServletRequest.getRequestURI());
                return new ResponseEntity<>(messageDTO, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        MessageDTO messageDTO = new MessageDTO();
        List<FieldErrorDTO> errors = bindingResult
                .getFieldErrors()
                .stream()
                .map(fieldError -> new FieldErrorDTO(
                        fieldError.getField(),
                        fieldError.getCode(),
                        fieldError.getRejectedValue()
                )).collect(Collectors.toList());
        List<ErrorDTO> globalErrors = bindingResult
                .getGlobalErrors()
                .stream()
                .map(objectError -> new ErrorDTO(objectError.getCode()))
                .collect(Collectors.toList());
        messageDTO.setTime(LocalDateTime.now().toString());
        messageDTO.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        messageDTO.setMessage(ex.getMessage());
        if (errors != null && !errors.isEmpty()) {
            messageDTO.setErrors(errors);
        }
        if (globalErrors != null && !globalErrors.isEmpty()) {
            messageDTO.setGlobalErrors(globalErrors);
        }
        return  new ResponseEntity<>(messageDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }
}
