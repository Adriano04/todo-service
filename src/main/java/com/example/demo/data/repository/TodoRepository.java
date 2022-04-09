package com.example.demo.data.repository;

import com.example.demo.data.model.TodoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository
        extends CrudRepository<TodoEntity, Long>,
            PagingAndSortingRepository<TodoEntity, Long> {
}
