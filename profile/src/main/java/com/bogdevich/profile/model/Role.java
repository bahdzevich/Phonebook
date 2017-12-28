package com.bogdevich.profile.model;

import javax.persistence.*;

/**
 * Class represents user role.
 *
 * @author Eugene Bogdevich
 * @version 1.0
 */
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
}
