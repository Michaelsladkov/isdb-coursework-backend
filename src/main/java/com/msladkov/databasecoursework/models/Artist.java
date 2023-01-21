package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;

@Entity
@Table(name = "ARTIST")
public class Artist {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "artist_id_seq_generator")
    @SequenceGenerator(name = "artist_id_seq_generator", sequenceName = "ARTIST_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SKILL")
    private String skill;

    @Column(name = "BIOGRAPHY")
    private String biography;
}
