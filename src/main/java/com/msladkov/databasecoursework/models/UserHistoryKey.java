package com.msladkov.databasecoursework.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class UserHistoryKey implements Serializable {
    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "PLAY_ID")
    private Long playId;

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UserHistoryKey other) {
            return other.userId.equals(this.userId) && other.playId.equals(this.playId);
        } else return false;
    }

    @Override
    public int hashCode() {
        return userId.intValue() * 12395 + playId.intValue();
    }
}
