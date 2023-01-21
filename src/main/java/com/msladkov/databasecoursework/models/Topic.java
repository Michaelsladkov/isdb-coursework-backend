package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "TOPIC")
@Data
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "topic_id_seq_generator")
    @SequenceGenerator(name = "topic_id_seq_generator", sequenceName = "TOPIC_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;
}
