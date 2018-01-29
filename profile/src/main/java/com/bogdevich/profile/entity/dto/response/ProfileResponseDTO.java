package com.bogdevich.profile.entity.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Set;

/**
 * POJO class for {@link com.bogdevich.profile.entity.domain.Profile} api response.
 *
 * @author Eugene Bogdevich
 */
public class ProfileResponseDTO {
    private Long id;
    private String email;
    @JsonIgnore
    private String password;
    private String name;
    private String lastname;
    private String skype;
    private String phone;
    private int room;
    private Set<RoleResponseDTO> roles;
    private Set<ProjectResponseDTO> projects;

    public ProfileResponseDTO() {
    }

    public ProfileResponseDTO(Long id, String email, String password, String name, String lastname, String skype, String phone, int room, Set<RoleResponseDTO> roles, Set<ProjectResponseDTO> projects) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.lastname = lastname;
        this.skype = skype;
        this.phone = phone;
        this.room = room;
        this.roles = roles;
        this.projects = projects;
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getSkype() {
        return skype;
    }

    public String getPhone() {
        return phone;
    }

    public int getRoom() {
        return room;
    }

    public Set<RoleResponseDTO> getRoles() {
        return roles;
    }

    public Set<ProjectResponseDTO> getProjects() {
        return projects;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public void setRoles(Set<RoleResponseDTO> roles) {
        this.roles = roles;
    }

    public void setProjects(Set<ProjectResponseDTO> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProfileResponseDTO that = (ProfileResponseDTO) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (lastname != null ? !lastname.equals(that.lastname) : that.lastname != null) return false;
        if (skype != null ? !skype.equals(that.skype) : that.skype != null) return false;
        return phone != null ? phone.equals(that.phone) : that.phone == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (skype != null ? skype.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Profile: {" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", skype='" + skype + '\'' +
                ", phone='" + phone + '\'' +
                ", room=" + room +
                ", roles=" + roles +
                ", projects=" + projects +
                '}';
    }
}
