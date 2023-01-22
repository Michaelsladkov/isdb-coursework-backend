package com.msladkov.databasecoursework.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ComposerRepresentation {
    private String name;
    private String biography;
    private Date birthDate;
    private Date deathDate;
}
