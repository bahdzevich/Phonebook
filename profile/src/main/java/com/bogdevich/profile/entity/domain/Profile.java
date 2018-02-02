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

    @Column(name = "email", unique = true)
    private String email;

    @Column(table = "password", name = "hash")
    @JsonIgnore
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "skype", unique = true)
    private String skype;

    @Column(name = "phone", unique = true)
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

    public Set<Role> getRoles() {
        return roles;
    }

    public Set<Project> getProjects() {
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

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Profile profile = (Profile) o;

        if (id != null ? !id.equals(profile.id) : profile.id != null) return false;
        if (email != null ? !email.equals(profile.email) : profile.email != null) return false;
        if (name != null ? !name.equals(profile.name) : profile.name != null) return false;
        if (lastname != null ? !lastname.equals(profile.lastname) : profile.lastname != null) return false;
        return phone != null ? phone.equals(profile.phone) : profile.phone == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Profile{" +
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
