package com.msladkov.databasecoursework.dto;

import lombok.Data;

@Data
public class SiteRepresentation {
    private String name;
    private String address;
    private String type;
    private int capacity;
    private int managerId;
}
