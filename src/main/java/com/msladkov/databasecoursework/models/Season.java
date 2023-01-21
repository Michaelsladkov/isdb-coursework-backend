package com.msladkov.databasecoursework.models;

import com.msladkov.databasecoursework.dto.SeasonRepresentation;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "SEASON")
@Data
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

    protected Season() {}

    public Season(SeasonRepresentation representation) {
        this.startDate = representation.getStartDate();
        this.endDate = representation.getEndDate();
        this.name = representation.getName();
    }
}
