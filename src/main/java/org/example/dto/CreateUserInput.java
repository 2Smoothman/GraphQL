package org.example.dto;

import java.util.List;

import lombok.Data;

@Data
public class CreateUserInput {
    private String username;
    private String email;
    private String password;
    private ProfileInput profile;
    private List<String> recommendations;
    private List<String> connections;
}
