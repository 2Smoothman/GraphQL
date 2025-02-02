package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "User profile data")
public class ProfileInput {
    @Schema(description = "First name", example = "John")
    private String firstName;
    
    @Schema(description = "Last name", example = "Doe")
    private String lastName;
    
    private String aboutMe;
    private String pictureUrl;
    private BioInput bio;
}