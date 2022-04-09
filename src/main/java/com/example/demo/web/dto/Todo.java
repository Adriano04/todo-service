package com.example.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Todo extends Auditable {

    @Schema(description = "The id of the Todo.")
    private Long id;

    @Schema(description = "The description of the Todo.", required = true)
    private String description;

    public Todo(String description) {
        this.description = description;
    }
}
