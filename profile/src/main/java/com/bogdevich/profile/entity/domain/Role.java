package com.bogdevich.profile.entity.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * Simple JavaBean class that represents profile role.
 *
 * @author Eugene Bogdevich
 */
@Entity
@Table(name = "role")
public class Role {

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
            mappedBy = "roles"
    )
    private Set<Profile> profiles;

    public Role() {
    }
}
