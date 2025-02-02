package org.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bios")
@Data
@NoArgsConstructor
public class Bio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @Column(columnDefinition = "TEXT")
    private String interests;
    
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "profile_id")
    private Profile profile;
    
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
} 