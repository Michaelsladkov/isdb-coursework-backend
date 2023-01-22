package com.msladkov.databasecoursework.models;

import com.msladkov.databasecoursework.dto.ComposerRepresentation;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "COMPOSER")
@Data
public class Composer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "composer_id_seq_generator")
    @SequenceGenerator(name = "composer_id_seq_generator", sequenceName = "COMPOSER_ID_SEQ", allocationSize = 1)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @Column(name = "DEATH_DATE")
    private Date deathDate;

    @Column(name = "BIOGRAPHY")
    private String biography;

    protected Composer() {}

    public Composer(ComposerRepresentation representation) {
        this.name = representation.getName();
        this.birthDate = representation.getBirthDate();
        this.deathDate = representation.getDeathDate();
        this.biography = representation.getBiography();
    }
}
