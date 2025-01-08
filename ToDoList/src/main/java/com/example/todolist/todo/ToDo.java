package com.example.todolist.todo;

import org.springframework.data.annotation.Id;

import org.springframework.data.annotation.Version;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table(name = "todolist")
public record ToDo(
        @Column("userid")
        Integer userId,
        @Id
        Integer id,
        String title,

        Boolean completed,
        @Version
        Integer version
) {
}
