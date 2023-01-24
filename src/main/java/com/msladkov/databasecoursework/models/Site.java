package com.msladkov.databasecoursework.models;

import com.msladkov.databasecoursework.dto.SiteRepresentation;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SITE")
@Data
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

    @ManyToOne
    @JoinColumn(name = "MANAGER_ID")
    private User manager;

    @Column(name = "INTEREST_THRESHOLD")
    private Integer interestThreshold;

    protected Site(){}

    public Site(SiteRepresentation siteRepresentation, User manager) {
        this.name = siteRepresentation.getName();
        this.address = siteRepresentation.getAddress();
        this.type = siteRepresentation.getType();
        this.capacity =  siteRepresentation.getCapacity();
        this.manager = manager;
        this.interestThreshold = siteRepresentation.getInterestThreshold();
    }
}
