package com.msladkov.databasecoursework.dto;

import lombok.Data;

@Data
public class AddCompositionRequest implements NoSpecificSiteManagerOrAdminRequest {
    private UserLoginData loginData;
    private CompositionRepresentation representation;
}
