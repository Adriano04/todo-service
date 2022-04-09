package com.example.demo.service.todo;

import com.example.demo.web.dto.Todo;
import com.example.demo.web.dto.TodoPage;


import java.util.List;

public interface TodoService {

    Todo getTodoById(long id);

    TodoPage listAllTodosPaged(int page, int size);

    List<Todo> listAllTodos();

    Todo addTodo(Todo todo);

    Todo updateTodo(long id, Todo todo);

    void deleteTodo(long id);
}
