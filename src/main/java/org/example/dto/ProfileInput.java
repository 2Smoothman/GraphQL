package org.example.dto;

import lombok.Data;

@Data
public class ProfileInput {
    private String firstName;
    private String lastName;
    private BioInput bio;
}