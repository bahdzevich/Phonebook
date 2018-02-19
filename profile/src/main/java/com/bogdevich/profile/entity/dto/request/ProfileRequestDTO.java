package com.bogdevich.profile.entity.dto.request;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * POJO class for {@link com.bogdevich.profile.entity.domain.Profile} api request.
 *
 * @author Eugene Bogdevich
 */
public class ProfileRequestDTO {
    @NotBlank(message = "Email is blank")
    @Email(message = "Email is not valid")
    @Size(max = 255, message = "Too much length")
    private String email;

    @NotBlank
    @Size(max = 45)
    @Pattern(regexp = "^([A-Z][a-z]*)(['-][A-Z][a-z]*)*$")
    private String name;

    @NotBlank
    @Size(max = 45)
    @Pattern(regexp = "^([A-Z][a-z]*)(['-][A-Z][a-z]*)*$")
    private String lastname;

    @NotBlank
    @Size(max = 45)
    @Pattern(regexp = "^([A-Za-z\\d]+)([-. ][A-Za-z\\d]+)*$")
    private String skype;

    @NotBlank(message = "")
    @Pattern(regexp = "^(\\+375(25|29|33|44)\\d{7})$")
    private String phone;

    @Min(value = 0)
    @Max(value = 99999)
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
