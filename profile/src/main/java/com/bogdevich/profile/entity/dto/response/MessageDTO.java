package com.bogdevich.profile.entity.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;


/**
 * Simple POJO for representing exception message.
 *
 * @author Eugene Bogdevich
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageDTO {

    private String time;
    private int status;
    private String message;
    private String path;
    private List<FieldErrorDTO> errors;
    private List<ErrorDTO> globalErrors;

    public MessageDTO() {
    }

    public MessageDTO(String time, int status, String message, String path, List<FieldErrorDTO> errors, List<ErrorDTO> globalErrors) {
        this.time = time;
        this.status = status;
        this.message = message;
        this.path = path;
        this.errors = errors;
        this.globalErrors = globalErrors;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<FieldErrorDTO> getErrors() {
        return errors;
    }

    public void setErrors(List<FieldErrorDTO> errors) {
        this.errors = errors;
    }

    public List<ErrorDTO> getGlobalErrors() {
        return globalErrors;
    }

    public void setGlobalErrors(List<ErrorDTO> globalErrors) {
        this.globalErrors = globalErrors;
    }
}
