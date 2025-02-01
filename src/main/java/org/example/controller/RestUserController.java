package org.example.controller;

import java.util.List;

import org.example.model.User;
import org.example.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class RestUserController {
    private final UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

        @GetMapping("/recommendations/{userId}")
    public ResponseEntity<List<User>> getUserRecommendations(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null && user.getRecommendations() != null) {
            return ResponseEntity.ok(userService.getUsersByIds(user.getRecommendations()));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/connections/{userId}")
    public ResponseEntity<List<User>> getUserConnections(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null && user.getConnections() != null) {
            return ResponseEntity.ok(userService.getUsersByIds(user.getConnections()));
        }
        return ResponseEntity.notFound().build();
    }
} 