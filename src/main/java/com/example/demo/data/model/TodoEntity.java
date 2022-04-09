package com.example.demo.data.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "todo")
@Getter
@Setter
@NoArgsConstructor
public class TodoEntity extends AuditableEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private String description;

    public TodoEntity(String description) {
        this.description = description;
    }
}
