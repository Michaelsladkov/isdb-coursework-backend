package com.msladkov.databasecoursework.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlayRepresentation {
    private String name;
    private Long compositionId;
    private Long siteId;
    private LocalDateTime dateTime;
    private Long seasonId;
    private String description;
}
