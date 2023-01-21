package com.msladkov.databasecoursework.dto;

import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;

@Data
public class SeasonRepresentation {
    private Date startDate;

    private Date endDate;

    private String name;
}
