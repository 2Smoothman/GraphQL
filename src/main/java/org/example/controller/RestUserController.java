package org.example.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.example.model.User;
import org.example.service.UserService;
import org.example.dto.UserConnectionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.security.core.Authentication;
import org.springframework.http.MediaType;

import lombok.RequiredArgsConstructor;
import org.example.dto.CreateUserInput;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "Users", description = "API для работы с пользователями")
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
    public ResponseEntity<List<UserConnectionResponse>> getUserRecommendations(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null && user.getRecommendations() != null) {
            List<UserConnectionResponse> recommendations = userService.getUsersByIds(user.getRecommendations())
                .stream()
                .map(UserConnectionResponse::new)
                .collect(Collectors.toList());
            return ResponseEntity.ok(recommendations);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/connections/{userId}")
    public ResponseEntity<List<UserConnectionResponse>> getUserConnections(@PathVariable Long userId) {
        User user = userService.getUserById(userId);
        if (user != null && user.getConnections() != null) {
            List<UserConnectionResponse> connections = userService.getUsersByIds(user.getConnections())
                .stream()
                .map(UserConnectionResponse::new)
                .collect(Collectors.toList());
            return ResponseEntity.ok(connections);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {
        String username = authentication.getName();
        User user = userService.getUserByUsername(username);
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Создание нового пользователя")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Пользователь успешно создан"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> createUser(@RequestBody CreateUserInput input) {
        User user = userService.createUserWithProfile(input);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userInput) {
        User updatedUser = userService.updateUser(id, userInput);
        return ResponseEntity.ok(updatedUser);
    }
} 