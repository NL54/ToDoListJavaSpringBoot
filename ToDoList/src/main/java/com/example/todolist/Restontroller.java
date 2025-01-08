package com.example.todolist;

import com.example.todolist.todo.ToDo;
import com.example.todolist.todo.ToDoRepository;
import com.example.todolist.user.JdbcUserRepository;
import com.example.todolist.user.UserData;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class Restontroller {
    private final JdbcUserRepository jdbcUserRepository;
    private final ToDoRepository toDoRepository;

    public Restontroller(JdbcUserRepository jdbcUserRepository, ToDoRepository toDoRepository) {
        this.jdbcUserRepository = jdbcUserRepository;
        this.toDoRepository = toDoRepository;
    }

    //USER CONTROLLER
    //get
    @GetMapping("/user")
    List<UserData> findAllUser(){
        return jdbcUserRepository.findAll();
    }

    @GetMapping("/user/{id}")
    Optional<UserData> findUserById(@PathVariable int id){
        return jdbcUserRepository.findById(id);
    }

    //create
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/user")
    void createUser(@RequestBody UserData user){
        jdbcUserRepository.createUsers(user);
    }

    //put
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/user/{id}")
    void updateUser(@PathVariable int id, @RequestBody UserData user){
        jdbcUserRepository.update(user,id);
    }

    //delete
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/user/{id}")
    void DeleteUser(@PathVariable int id){
        jdbcUserRepository.delete(id);
    }

    // TODO CONTROLLER
    //GET
    @GetMapping("/toDo")
    List<ToDo> findAllToDo(){
        return toDoRepository.findAll();
    }

    @GetMapping("/toDo/{id}")
    Optional<ToDo> findToDoById(@PathVariable Integer id){
        return toDoRepository.findById(id);
    }

    //POST
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/toDo")
    void createToDo(@RequestBody ToDo toDo){
        toDoRepository.save(toDo);
    }

    //PUT
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/toDo")
    void updateToDo(@RequestBody ToDo toDo){
        toDoRepository.save(toDo);
    }

    //DELETE
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/toDo/{id}")
    void DeleteToDo(@PathVariable Integer id){
        toDoRepository.deleteById(id);
    }
}
