package com.msladkov.databasecoursework.models;

import jakarta.persistence.Column;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserPlannedKey implements Serializable {
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "PLAY_ID")
    private int playId;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserPlannedKey other) {
            return other.userId == this.userId && other.playId == this.playId;
        } else return false;
    }

    @Override
    public int hashCode() {
        return userId * 12395 + playId;
    }
}
