package org.example.dto;

import java.util.Set;

import lombok.Data;

@Data
public class CreateUserInput {
    private String username;
    private String email;
    private String password;
    private ProfileInput profile;
    private BioInput bio;
    private Set<String> recommendations;
    private Set<String> connections;
}
