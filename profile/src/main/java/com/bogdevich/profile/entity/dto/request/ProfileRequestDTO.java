package com.bogdevich.profile.entity.dto.request;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;

/**
 * POJO class for {@link com.bogdevich.profile.entity.domain.Profile} api request.
 *
 * @author Eugene Bogdevich
 */
public class ProfileRequestDTO {
    @Email
    private String email;
    @NotBlank(message = "profile.empty.name")
    private String name;
    @NotBlank
    private String lastname;
    @NotBlank
    private String skype;
    @NotBlank
    private String phone;
    @Min(0)
    private int room;

    public ProfileRequestDTO() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getSkype() {
        return skype;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "ProfileRequestDTO{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", skype='" + skype + '\'' +
                ", phone='" + phone + '\'' +
                ", room=" + room +
                '}';
    }
}
