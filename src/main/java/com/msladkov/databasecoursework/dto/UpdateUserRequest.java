package com.msladkov.databasecoursework.dto;

import com.msladkov.databasecoursework.models.UserRole;
import lombok.Data;

@Data
public class UpdateUserRequest {
    private String userEmailToUpdate;
    private String email;
    private String password;
    private UserRole newRole;
}
