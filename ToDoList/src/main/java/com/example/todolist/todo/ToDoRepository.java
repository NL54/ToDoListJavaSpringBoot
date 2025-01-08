package com.example.todolist.todo;

import org.springframework.data.repository.ListCrudRepository;

import java.util.List;

public interface ToDoRepository extends ListCrudRepository<ToDo,Integer> {
    List<ToDo> findAllByCompleted(boolean completed);





}
