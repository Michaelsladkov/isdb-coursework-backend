package com.msladkov.databasecoursework.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public final class UserWishKey implements Serializable {
    @Column(name = "USER_ID")
    private int userId;

    @Column(name = "COMPOSITION_ID")
    private int compositionId;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserWishKey other) {
            return other.userId == this.userId && other.compositionId == this.compositionId;
        } else return false;
    }

    @Override
    public int hashCode() {
        return userId * 12395 + compositionId;
    }
}
