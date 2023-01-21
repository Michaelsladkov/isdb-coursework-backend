package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "SEASON")
public class Season {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "season_id_seq_generator")
    @SequenceGenerator(name = "season_id_seq_generator", sequenceName = "SEASON_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "START_DATE")
    private Date startDate;

    @Column(name = "END_DATE")
    private Date endDate;

    @Column(name = "NAME")
    private String name;
}
