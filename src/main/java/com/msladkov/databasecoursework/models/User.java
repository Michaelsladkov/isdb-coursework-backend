package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;

@Entity
@Table(name = "USER")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_generator")
    @SequenceGenerator(name = "user_id_seq_generator", sequenceName = "USER_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne
    @JoinColumn(name = "id")
    private UserAuthData authData;
}
