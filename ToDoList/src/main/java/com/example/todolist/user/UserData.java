package com.example.todolist.user;

import org.springframework.data.relational.core.mapping.Table;

@Table(name = "users")
public record UserData(
        Integer id,
        String name,
        String username,
        String email,
        String phone,
        String website
) {
}
