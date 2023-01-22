package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "PLANNED_PLAYS")
public class PlannedPlay {
    @EmbeddedId
    private UserPlannedKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @MapsId("playId")
    @JoinColumn(name = "PLAY_ID")
    private Play play;

    @Column(name = "ADDED")
    private Date added;
}
