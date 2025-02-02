package org.example.dto;

import java.util.Set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Data for creating a new user")
public class CreateUserInput {
    @Schema(description = "Username", example = "newuser", required = true)
    private String username;
    
    @Schema(description = "User email", example = "newuser@example.com", required = true)
    private String email;
    
    @Schema(description = "Password", example = "password123", required = true)
    private String password;
    
    @Schema(description = "User profile")
    private ProfileInput profile;
    
    @Schema(description = "User biography")
    private BioInput bio;
    
    @Schema(hidden = true)
    private Set<String> recommendations;
    
    @Schema(hidden = true)
    private Set<String> connections;
}
