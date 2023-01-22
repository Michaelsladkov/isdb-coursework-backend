package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "NOTIFICATIONS")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_seq_generator")
    @SequenceGenerator(name = "notification_seq_generator", sequenceName = "NOTIFICATION_ID_SEQ")
    private Long id;

    @Column(name = "IS_SHOWN")
    private boolean isShown;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private NotificationType type;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User targetUser;

    @ManyToOne
    @JoinColumn(name = "PLAY_ID")
    private Play play;
}
