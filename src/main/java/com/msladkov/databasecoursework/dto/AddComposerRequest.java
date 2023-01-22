package com.msladkov.databasecoursework.dto;

import lombok.Data;

@Data
public class AddComposerRequest implements NoSpecificSiteManagerOrAdminRequest {
    private UserLoginData loginData;
    private ComposerRepresentation composerRepresentation;
}
