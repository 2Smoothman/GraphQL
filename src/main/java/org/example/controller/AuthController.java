package org.example.controller;

import org.example.dto.AuthResponse;
import org.example.dto.SignInRequest;
import org.example.dto.SignUpRequest;
import org.example.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "API аутентификации")
public class AuthController {
    private final AuthService authService;

    @Operation(summary = "Регистрация нового пользователя")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешная регистрация"),
        @ApiResponse(responseCode = "400", description = "Некорректные данные")
    })
    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@RequestBody SignUpRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @Operation(summary = "Вход в систему")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Успешный вход"),
        @ApiResponse(responseCode = "401", description = "Неверные учетные данные")
    })
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> signin(@RequestBody SignInRequest request) {
        return ResponseEntity.ok(authService.signin(request));
    }
} 