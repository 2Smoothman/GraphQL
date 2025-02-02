package org.example.dto;

import org.example.model.User;

import lombok.Data;

@Data
public class UserConnectionResponse {
    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String lastName;
    
    public UserConnectionResponse(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        if (user.getProfile() != null) {
            this.firstName = user.getProfile().getFirstName();
            this.lastName = user.getProfile().getLastName();
        }
    }
} 