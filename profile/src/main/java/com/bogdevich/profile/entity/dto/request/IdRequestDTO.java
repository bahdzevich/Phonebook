package com.bogdevich.profile.entity.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class IdRequestDTO {

    @NotNull
    @Min(0)
    @Max(Long.MAX_VALUE)
    private Long id;

    public IdRequestDTO() {
    }

    public IdRequestDTO(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
