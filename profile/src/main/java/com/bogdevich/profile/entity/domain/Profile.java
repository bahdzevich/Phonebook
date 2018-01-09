package com.bogdevich.profile.entity.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple JavaBean object that represents customer profile.
 *
 * @author Eugene Bogdevich
 */
@Entity
@Table(name = "user")
@SecondaryTable(
        name = "password",
        pkJoinColumns = @PrimaryKeyJoinColumn(
                name = "user_id",
                referencedColumnName = "id"
        )
)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(table = "password", name = "hash")
    @JsonIgnore
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "skype")
    private String skype;

    @Column(name = "phone")
    private String phone;

    @Column(name = "room")
    private int room;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @JsonManagedReference
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_projects",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id")
    )
    private Set<Project> projects;

    public Profile() {
    }
}
