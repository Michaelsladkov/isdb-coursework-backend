package com.msladkov.databasecoursework.dto;

import lombok.Data;

@Data
public class AddSeasonRequest {
    private UserLoginData loginData;
    private SeasonRepresentation season;
}
