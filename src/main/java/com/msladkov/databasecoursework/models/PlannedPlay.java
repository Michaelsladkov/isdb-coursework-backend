package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "PLANNED_PLAYS")
@NoArgsConstructor
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

    public PlannedPlay(User user, Play play) {
        this.user = user;
        this.play = play;
        this.added = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
        this.id = new UserPlannedKey(user.getId(), play.getId());
    }
}
