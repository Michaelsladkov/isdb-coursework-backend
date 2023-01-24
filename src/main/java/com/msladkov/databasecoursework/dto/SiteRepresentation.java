package com.msladkov.databasecoursework.dto;

import com.msladkov.databasecoursework.models.Site;
import lombok.Data;

@Data
public class SiteRepresentation {
    private String name;
    private String address;
    private String type;
    private int capacity;
    private int managerId;
    private String managerName;
    private int interestThreshold;

    public SiteRepresentation(Site site) {
        this.name = site.getName();
        this.address = site.getAddress();
        this.capacity = site.getCapacity();
        this.managerName = site.getManager().getName();
        this.interestThreshold = site.getInterestThreshold();
        this.type = site.getType();
    }
}
