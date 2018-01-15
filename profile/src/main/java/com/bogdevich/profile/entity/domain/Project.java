package com.bogdevich.profile.entity.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple JavaBean that represents customer projects.
 *
 * @author Eugene Bogdevich
 */
@Entity
@Table(name = "project")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "projects"
    )
    private Set<Profile> profiles;

    public Project() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Profile> getProfiles() {
        return profiles;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProfiles(Set<Profile> profiles) {
        this.profiles = profiles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Project project = (Project) o;

        if (id != null ? !id.equals(project.id) : project.id != null) return false;
        return name != null ? name.equals(project.name) : project.name == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profiles=" + profiles +
                '}';
    }
}
