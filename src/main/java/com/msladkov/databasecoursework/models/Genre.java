package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "GENRE")
@Data
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_id_seq_generator")
    @SequenceGenerator(name = "genre_id_seq_generator", sequenceName = "GENRE_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;
}
