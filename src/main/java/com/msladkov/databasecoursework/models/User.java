package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq_generator")
    @SequenceGenerator(name = "user_id_seq_generator", sequenceName = "USER_ID_SEQ", allocationSize = 1)
    @Getter
    private Long id;

    @Column(name = "NAME")
    @Getter
    private String name;

    @Column(name = "EMAIL")
    @Getter
    private String email;

    @Column(name = "PASSWORD_HASH")
    private String passwordHash;

    public String obtainPasswordHash() {
        return passwordHash;
    }

    @Column(name = "ROLE")
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private UserRole role;

    protected User(){}

    public User(String name, String email, String passwordHash) {
        this.name = name;
        this.email = email;
        this.role = UserRole.USER;
        this.passwordHash = passwordHash;
    }
}
