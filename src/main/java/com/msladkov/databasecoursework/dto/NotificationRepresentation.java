package com.msladkov.databasecoursework.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationRepresentation {
    private String caption;
    private Long playId;
}
