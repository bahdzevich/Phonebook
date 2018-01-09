package com.bogdevich.profile.entity.dto;

import java.util.Objects;

/**
 * Simple POJO for representing exception message.
 *
 * @author Eugene Bogdevich
 */
public class MessageDTO {

    private String time;
    private int status;
    private String message;

    public MessageDTO() {
    }

    public MessageDTO(String time, int status, String message) {
        this.time = time;
        this.status = status;
        this.message = message;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MessageDTO)) return false;
        MessageDTO that = (MessageDTO) o;
        return status == that.status &&
                Objects.equals(time, that.time) &&
                Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(time, status, message);
    }

    @Override
    public String toString() {
        return "MessageDTO{" + "time='" + time + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
