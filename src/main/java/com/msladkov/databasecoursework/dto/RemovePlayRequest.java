package com.msladkov.databasecoursework.dto;

import lombok.Data;

@Data
public class RemovePlayRequest implements AuthorizedRequest {
    private UserLoginData loginData;
    private Long playId;
}
