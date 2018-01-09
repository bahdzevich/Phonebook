package com.bogdevich.profile.entity.dto;

import java.util.Set;

/**
 * POJO class for {@link com.bogdevich.profile.entity.domain.Profile} transfer.
 *
 * @author Eugene Bogdevich
 */
public class ProfileDTO {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String lastname;
    private String skype;
    private String phone;
    private int room;
    private Set<RoleDTO> roles;

    public ProfileDTO() {
    }
}
