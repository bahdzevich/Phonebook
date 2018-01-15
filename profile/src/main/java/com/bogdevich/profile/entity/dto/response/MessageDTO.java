package com.bogdevich.profile.entity.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Objects;

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
    private Object body;

    public MessageDTO() {
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

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
