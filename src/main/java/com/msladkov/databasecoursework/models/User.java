package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "USER")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_generator")
    @SequenceGenerator(name = "user_id_seq_generator", sequenceName = "USER_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD_HASH")
    private String passwordHash;

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    private UserRole role;

    protected User(){}

    public User(String name, String email, String passwordHash) {
        this.name = name;
        this.email = email;
        this.role = UserRole.USER;
        this.passwordHash = passwordHash;
    }
}
