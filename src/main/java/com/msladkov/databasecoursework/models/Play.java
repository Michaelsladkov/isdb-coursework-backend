package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "PLAY")
public class Play {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "play_id_seq_generator")
    @SequenceGenerator(name = "play_id_seq_generator", sequenceName = "PLAY_ID_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPOSITION_ID")
    private Composition composition;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SITE_ID")
    private Site site;

    @Column(name = "DATE")
    private LocalDateTime date;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "SEASON_ID")
    private Season season;
}
