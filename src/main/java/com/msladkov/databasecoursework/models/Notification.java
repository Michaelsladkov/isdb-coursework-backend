package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Table(name = "NOTIFICATIONS")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notification_seq_generator")
    @SequenceGenerator(name = "notification_seq_generator", sequenceName = "NOTIFICATION_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "IS_SHOWN")
    private boolean isShown;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    @Getter
    private NotificationType type;

    @Column(name = "CREATION_DATE")
    private LocalDateTime creationDate;

    @Column(name = "USER_ID")
    private Long targetUser;

    @Column(name = "PLAY_ID")
    @Getter
    private Long play;
}
