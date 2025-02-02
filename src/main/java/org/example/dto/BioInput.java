package org.example.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Biographical data")
public class BioInput {
    @Schema(description = "Description", example = "Description")
    private String description;
    
    @Schema(description = "Interests", example = "Interests")
    private String interests;
}