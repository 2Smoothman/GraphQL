package org.example.dto;

import lombok.Data;

@Data
public class CreateUserInput {
    private String username;
    private String email;
    private String password;
    private ProfileInput profile;
}
