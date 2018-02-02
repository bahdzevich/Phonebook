package com.bogdevich.auth.entity.dto;

/**
 * Used for converting exception message.
 *
 * @author Eugene Bogdevich
 * @version 1.0
 */
public class ExceptionMessageDTO {
    private String time;
    private int status;
    private String message;

    public ExceptionMessageDTO() {
    }

    public ExceptionMessageDTO(String time, int status, String message) {
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
        if (o == null || getClass() != o.getClass()) return false;

        ExceptionMessageDTO that = (ExceptionMessageDTO) o;

        if (status != that.status) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        return message != null ? message.equals(that.message) : that.message == null;
    }

    @Override
    public int hashCode() {
        int result = time != null ? time.hashCode() : 0;
        result = 31 * result + status;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ExceptionMessageDTO{" +
                "time='" + time + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                '}';
    }
}
