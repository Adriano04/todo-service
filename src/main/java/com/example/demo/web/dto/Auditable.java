package com.example.demo.web.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Auditable {

    @Schema(description = "The creation date.")
    private LocalDateTime createdDate;

    @Schema(description = "The last modification date.")
    private LocalDateTime lastModifiedDate;
}
