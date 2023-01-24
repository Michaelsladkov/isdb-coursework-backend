package com.msladkov.databasecoursework.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public final class UserWishKey implements Serializable {
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "COMPOSITION_ID")
    private Long compositionId;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserWishKey other) {
            return other.userId.equals(this.userId) && other.compositionId.equals(this.compositionId);
        } else return false;
    }

    @Override
    public int hashCode() {
        return userId.intValue() * 12395 + compositionId.intValue();
    }
}
