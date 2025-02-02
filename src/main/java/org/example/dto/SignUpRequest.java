package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Запрос на регистрацию")
public class SignUpRequest {
    @Schema(description = "Имя пользователя", example = "john_doe")
    private String username;
    
    @Schema(description = "Email пользователя", example = "john@example.com")
    private String email;
    
    @Schema(description = "Пароль", example = "password123")
    private String password;
} 