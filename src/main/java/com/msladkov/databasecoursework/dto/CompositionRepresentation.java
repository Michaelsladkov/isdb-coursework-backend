package com.msladkov.databasecoursework.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CompositionRepresentation {
    private String name;
    private String genre;
    private Long composerId;
    private Date creationDate;
    private String language;
    private List<String> topics;
}
