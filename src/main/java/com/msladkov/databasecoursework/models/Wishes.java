package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "WISHES")
public class Wishes {
    @EmbeddedId
    private UserWishKey id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "USER_ID")
    private User user;

    @ManyToOne
    @MapsId("compositionId")
    @JoinColumn(name = "COMPOSITION_ID")
    private Composition composition;

    @Column(name = "ADDED")
    private LocalDateTime addedDate;
}
