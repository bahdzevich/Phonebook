package com.bogdevich.profile.entity.dto.response;

/**
 * POJO class for {@link com.bogdevich.profile.entity.domain.Role} transfer.
 *
 * @author Eugene Bogdevich
 */
public class RoleResponseDTO {
    private Long id;
    private String name;

    public RoleResponseDTO() {
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

        RoleResponseDTO roleResponseDTO = (RoleResponseDTO) o;

        if (id != null ? !id.equals(roleResponseDTO.id) : roleResponseDTO.id != null) return false;
        return name != null ? name.equals(roleResponseDTO.name) : roleResponseDTO.name == null;
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
