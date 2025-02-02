package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Запрос на вход в систему")
public class SignInRequest {
    @Schema(description = "Имя пользователя", example = "john_doe")
    private String username;
    
    @Schema(description = "Пароль", example = "password123")
    private String password;
} 