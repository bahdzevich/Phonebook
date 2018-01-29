package com.bogdevich.profile.entity.dto.request;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RoleRequestDTO {
    @NotBlank
    @Size(max = 45)
    @Pattern(regexp = "^([A-Z\\\\d])+([_]?[A-Z\\\\d]+)*$")
    private String name;

    public RoleRequestDTO() {
    }

    public RoleRequestDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
