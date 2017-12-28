package com.bogdevich.profile.model;

import javax.persistence.*;

/**
 * Class represents user project.
 *
 * @author Eugene Bogdevich
 * @version 1.0
 */
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;
}
