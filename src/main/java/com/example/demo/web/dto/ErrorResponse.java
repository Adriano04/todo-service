package com.example.demo.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ErrorResponse {

    @Schema(description = "The error code. Maps to the HTTP status codes.", required = true)
    private final int code;

    @Schema(description = "The error message.", required = true)
    private final String message;

    private ErrorResponse(HttpStatus status, String message) {
        this.code = status.value();
        this.message = message;
    }

    public static ErrorResponse of(HttpStatus status, String message) {
        return new ErrorResponse(status, message);
    }
}
