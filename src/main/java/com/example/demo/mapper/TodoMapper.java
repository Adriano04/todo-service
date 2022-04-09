package com.example.demo.mapper;

import com.example.demo.data.model.TodoEntity;
import com.example.demo.web.dto.Todo;
import org.mapstruct.Mapper;

@Mapper
public interface TodoMapper {

    Todo convertToDto(TodoEntity todoEntity);

    TodoEntity convertToEntity(Todo todoDto);
}
