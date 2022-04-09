package com.example.demo.service.todo;

import com.example.demo.mapper.TodoMapper;
import com.example.demo.web.dto.Todo;
import com.example.demo.web.dto.TodoPage;
import com.example.demo.data.model.TodoEntity;
import com.example.demo.data.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoRepository todoRepo;
    private final TodoMapper todoMapper;

    @Override
    public Todo getTodoById(long id) {
        return todoMapper.convertToDto(getTodoEntityById(id));
    }

    @Override
    public TodoPage listAllTodosPaged(int page, int size) {
        var todos = todoRepo.findAll(PageRequest.of(page, size, Sort.Direction.ASC, "id"));
        return TodoPage.of(
                todos.getNumber(),
                todos.getSize(),
                todos.getTotalPages(),
                todos.map(todoMapper::convertToDto).toList()
        );
    }

    @Override
    public List<Todo> listAllTodos() {
        var toDos = todoRepo.findAll();
        var toDoList = new ArrayList<Todo>();
        toDos.forEach(t -> toDoList.add(todoMapper.convertToDto(t)));
        return toDoList;
    }

    @Override
    public Todo addTodo(Todo todoDto) {
        var todo = new TodoEntity();
        todo.setDescription(todoDto.getDescription());
        return todoMapper.convertToDto(todoRepo.save(todo));
    }

    @Override
    public Todo updateTodo(long id, Todo todoDto) {
        var todo = getTodoEntityById(id);
        todo.setDescription(todoDto.getDescription());
        return todoMapper.convertToDto(todoRepo.save(todo));
    }

    @Override
    public void deleteTodo(long id) {
        todoRepo.delete(getTodoEntityById(id));
    }

    private TodoEntity getTodoEntityById(long id) {
        return todoRepo.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No Todo with id " + id + " found."));
    }
}
