package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;

@Entity
@Table(name = "HISTORY")
public class History {
    @EmbeddedId
    private UserHistoryKey id;
    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @MapsId("playId")
    @JoinColumn(name = "PLAY_ID")
    private Play play;

    @Column(name = "REVIEW")
    private String review;

    @Column(name = "TO_BE_REVIEWED")
    private boolean toBeReviewed;
}
