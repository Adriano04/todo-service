package com.example.demo.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class TodoPage extends Page<Todo> {

    private TodoPage(int page, int size, int totalPages, List<Todo> todos) {
        super(page, size, totalPages, todos);
    }

    public static TodoPage of(int page, int size, int totalPages, List<Todo> todos) {
        return new TodoPage(page, size, totalPages, todos);
    }
}
