package com.bogdevich.profile.entity.dto.response;

public class ErrorDTO {
    String code;

    public ErrorDTO() {
    }

    public ErrorDTO(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
