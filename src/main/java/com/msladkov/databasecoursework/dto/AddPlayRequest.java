package com.msladkov.databasecoursework.dto;

import lombok.Data;

@Data
public class AddPlayRequest implements SiteManagerRequest {
    private UserLoginData loginData;
    private PlayRepresentation play;
}
