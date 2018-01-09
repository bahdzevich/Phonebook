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

    @Column(
            name = "name",
            unique = true
    )
    private String name;

    @ManyToMany(
            fetch = FetchType.LAZY,
            mappedBy = "projects"
    )
    private Set<Profile> profiles;

}
