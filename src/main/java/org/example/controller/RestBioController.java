package org.example.controller;

import org.example.dto.UpdateBioRequest;
import org.example.model.Bio;
import org.example.service.BioService;
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
@RequestMapping("/api/bios")
@RequiredArgsConstructor
public class RestBioController {
    private final BioService bioService;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Bio> getBio(@PathVariable Long id) {
        Bio bio = bioService.getBioById(id);
        return bio != null ? ResponseEntity.ok(bio) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bio> updateBio(@PathVariable Long id, @RequestBody UpdateBioRequest request) {
        Bio updatedBio = bioService.updateBio(id, request);
        return updatedBio != null ? ResponseEntity.ok(updatedBio) : ResponseEntity.notFound().build();
    }

    @GetMapping("/me")
    public ResponseEntity<Bio> getMyBio(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        if (user.getProfile() == null || user.getProfile().getBio() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user.getProfile().getBio());
    }
} 