package com.msladkov.databasecoursework.models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "PLAY")
@Data
public class Play implements Comparable<Play> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "play_id_seq_generator")
    @SequenceGenerator(name = "play_id_seq_generator", sequenceName = "PLAY_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

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

    @Column(name = "DESCRIPTION")
    private String description;

    @ManyToMany
    @JoinTable(name = "PLAY_ARTIST",
               joinColumns = @JoinColumn(name = "play_id"),
               inverseJoinColumns = @JoinColumn(name = "artist_id"))
    private List<Artist> artists;

    @Override
    public int compareTo(Play o) {
        return this.id.compareTo(o.id);
    }

    @Override
    public int hashCode() {
        return id.intValue();
    }
}
