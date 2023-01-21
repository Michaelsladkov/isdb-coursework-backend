package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;

@Entity
@Table(name = "SITE")
public class Site {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "site_id_seq_generator")
    @SequenceGenerator(name = "site_id_seq_generator", sequenceName = "SITE_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "TYPE")
    private String type;

    @Column(name = "CAPACITY")
    private int capacity;
}
