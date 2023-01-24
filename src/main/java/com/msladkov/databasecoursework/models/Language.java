package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "LANGUAGE")
@Data
public class Language {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "language_id_seq_generator")
    @SequenceGenerator(name = "language_id_seq_generator", sequenceName = "LANGUAGE_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;
}
