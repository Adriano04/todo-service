package com.example.demo.web.controller;

import com.example.demo.web.dto.ErrorResponse;
import com.example.demo.web.dto.Todo;
import com.example.demo.web.dto.TodoPage;
import com.example.demo.service.todo.TodoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "todo")
public class TodoController {

    private final TodoService toDoService;

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public ErrorResponse handleNotFound(NoSuchElementException exception) {
        return ErrorResponse.of(HttpStatus.NOT_FOUND, exception.getMessage());
    }

    @Operation(summary = "Gets all todos in a paged manner.")
    @ApiResponse(
            responseCode = "200",
            description = "A page of all todos.",
            content = @Content(schema = @Schema(implementation = TodoPage.class))
    )
    @GetMapping("/todo")
    public TodoPage getTodosPaged(
            @Parameter(description = "The page number.", required = true)
            @RequestParam("page") int page,
            @Parameter(description = "The page size.", required = true)
            @RequestParam("size") int size
    ) {
        return toDoService.listAllTodosPaged(page, size);
    }

    @Operation(summary = "Gets a specific todo by id.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "The Todo",
                    content = @Content(schema = @Schema(implementation = Todo.class))
            ),
            @ApiResponse(responseCode = "404", description = "Todo with specified id was not found.")
    })
    @GetMapping("/todo/{id:[0-9]+}")
    public Todo getTodo(
            @Parameter(description = "The id of the Todo to get.", required = true)
            @PathVariable("id") long id
    ) {
        return toDoService.getTodoById(id);
    }

    @Operation(summary = "Creates a new todo.")
    @ApiResponse(
            responseCode = "201",
            description = "The new Todo",
            content = @Content(schema = @Schema(implementation = Todo.class))
    )
    @PostMapping("/todo")
    @ResponseStatus(HttpStatus.CREATED)
    public Todo addTodo(
            @Parameter(description = "The Todo DTO to create.", required = true)
            @RequestBody Todo todoDto
    ) {
        return toDoService.addTodo(todoDto);
    }

    @Operation(summary = "Updates a specific todo by id.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "The updated Todo",
                    content = @Content(schema = @Schema(implementation = Todo.class))
            ),
            @ApiResponse(responseCode = "404", description = "Todo with specified id was not found.")
    })
    @PutMapping("/todo/{id:[0-9]+}")
    public Todo updateTodo(
            @Parameter(description = "The id of the Todo to update", required = true)
            @PathVariable("id") long id,
            @Parameter(description = "The Todo DTO with the updated data.", required = true)
            @RequestBody Todo todoDto
    ) {
        return toDoService.updateTodo(id, todoDto);
    }

    @Operation(summary = "Updates a specific todo by id.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "No content"),
            @ApiResponse(responseCode = "404", description = "Todo with specified id was not found.")
    })
    @DeleteMapping("/todo/{id:[0-9]+}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(
            @Parameter(description = "The id of the Todo to be deleted.", required = true)
            @PathVariable("id") long id
    ) {
        toDoService.deleteTodo(id);
    }
}
