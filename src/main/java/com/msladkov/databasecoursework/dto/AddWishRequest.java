package com.msladkov.databasecoursework.dto;

import lombok.Data;

@Data
public class AddWishRequest {
    private UserLoginData loginData;
    private Long compositionId;
}
