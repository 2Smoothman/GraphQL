package org.example.controller;

import org.example.dto.UpdateProfileRequest;
import org.example.model.Profile;
import org.example.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.example.service.UserService;
import org.example.model.User;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class RestProfileController {
    private final ProfileService profileService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long id) {
        Profile profile = profileService.getProfileById(id);
        return profile != null ? ResponseEntity.ok(profile) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> updateProfile(@PathVariable Long id, @RequestBody UpdateProfileRequest request) {
        Profile updatedProfile = profileService.updateProfile(id, request);
        return updatedProfile != null ? ResponseEntity.ok(updatedProfile) : ResponseEntity.notFound().build();
    }

    @GetMapping("/me")
    public ResponseEntity<Profile> getMyProfile(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        if (user.getProfile() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.getProfile());
    }
} 