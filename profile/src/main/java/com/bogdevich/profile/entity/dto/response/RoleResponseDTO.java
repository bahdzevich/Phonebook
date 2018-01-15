package com.bogdevich.profile.entity.dto;

import java.util.Set;

/**
 * POJO class for {@link com.bogdevich.profile.entity.domain.Role} transfer.
 *
 * @author Eugene Bogdevich
 */
public class RoleDTO {
    private Long id;
    private String name;

    public RoleDTO() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleDTO roleDTO = (RoleDTO) o;

        if (id != null ? !id.equals(roleDTO.id) : roleDTO.id != null) return false;
        return name != null ? name.equals(roleDTO.name) : roleDTO.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Role: {" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
