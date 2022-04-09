package com.example.demo.service.bootstrap;


import com.example.demo.service.todo.TodoService;
import com.example.demo.web.dto.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BootstrapService {

    private final TodoService todoService;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationRead(ApplicationReadyEvent event) {
        todoService.addTodo(new Todo("Learn Spring Boot"));
        todoService.addTodo(new Todo("Buy Groceries"));
        todoService.addTodo(new Todo("Read Mangas"));
        todoService.addTodo(new Todo("Play PlayStation"));
    }
}
