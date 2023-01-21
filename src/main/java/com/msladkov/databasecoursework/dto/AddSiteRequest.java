package com.msladkov.databasecoursework.dto;

import com.msladkov.databasecoursework.models.Site;
import lombok.Data;

@Data
public class AddSiteRequest {
    private UserLoginData loginData;
    private SiteRepresentation site;
}
