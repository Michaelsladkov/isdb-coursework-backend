package com.msladkov.databasecoursework.dto;

import lombok.Data;

@Data
public class AddPlanRequest {
    private UserLoginData loginData;
    private Long playId;
}
