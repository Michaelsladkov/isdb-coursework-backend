package com.msladkov.databasecoursework.models;

import com.msladkov.databasecoursework.dto.CompositionRepresentation;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Table(name = "composition")
@Entity
@Data
public class Composition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "composition_id_seq_generator")
    @SequenceGenerator(name = "composition_id_seq_generator", sequenceName = "COMPOSITION_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "GENRE_ID")
    private Genre genre;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPOSER_ID")
    private Composer composer;

    @Column(name = "CREATION_DATE")
    private Date creationDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "COMPOSITION_LANGUAGE",
            joinColumns = @JoinColumn(name = "composition_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private Language language;

    @ManyToMany
    @JoinTable(name = "COMPOSITION_TOPIC",
               joinColumns = @JoinColumn(name = "composition_id"),
               inverseJoinColumns = @JoinColumn(name = "topic_id"))
    private List<Topic> topics;

    protected Composition() {}

    public Composition(CompositionRepresentation representation,
                       List<Topic> topics,
                       Language language,
                       Composer composer,
                       Genre genre) {
        this.name = representation.getName();
        this.topics = topics;
        this.composer = composer;
        this.creationDate = representation.getCreationDate();
        this.language = language;
        this.genre = genre;
    }
}
