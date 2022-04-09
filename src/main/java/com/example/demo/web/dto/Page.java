package com.example.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Page<Elements> {

    @Schema(description = "The page number.", required = true)
    private int page;

    @Schema(description = "The page size.", required = true)
    private int size;

    @Schema(description = "The total amount of pages.", required = true)
    private int totalPages;

    @Schema(description = "A list of elements.", required = true)
    private List<Elements> elements = new ArrayList<>();
}
