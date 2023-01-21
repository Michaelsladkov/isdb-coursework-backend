package com.msladkov.databasecoursework.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "user_auth_data")
public class UserAuthData {
    @Id
    private long userId;

    @Column(name = "password_hash")
    private String passwordHash;

    @Column(name = "salt")
    private String salt;
}
