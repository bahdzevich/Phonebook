package com.bogdevich.profile.model;

import javax.persistence.*;

/**
 * Class represents user.
 *
 * @author Eugene Bogdevich
 * @version 1.0
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "lastname")
    private String lastname;

    @Column(name = "email")
    private String email;

    @Column(name = "skype")
    private String skype;

    @Column(name = "phone")
    private String phone;

    @Column(name = "room")
    private int room;
}
