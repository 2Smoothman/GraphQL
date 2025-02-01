package org.example.dto;

import lombok.Data;

@Data
public class UpdateProfileRequest {
    private String firstName;
    private String lastName;
    private String aboutMe;
    private String pictureUrl;
} 