package com.example.todolist.user;

import com.example.todolist.RestClient;
import com.example.todolist.todo.ToDo;
import com.example.todolist.todo.ToDoRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

//Fonction qui initialize les base de donnees avec des donnes de "https://jsonplaceholder.typicode.com/" si elles sont vides
@Component
public class DataLoader implements CommandLineRunner {
    private final JdbcUserRepository jdbcUserRepository;
    private final ObjectMapper objectMapper;
    private final RestClient restClient;
    private final ToDoRepository toDoRepository;

    public DataLoader(JdbcUserRepository jdbcUserRepository, ObjectMapper objectMapper, ToDoRepository toDoRepository, RestClient restClient) {
        this.jdbcUserRepository = jdbcUserRepository;
        this.objectMapper = objectMapper;
        this.restClient = restClient;
        this.toDoRepository = toDoRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        if(jdbcUserRepository.countUsers()==0){
            List<User> users = restClient.findAllUsers();
            List<UserData> userData = new ArrayList<UserData>();
            for (User user : users) {
                userData.add(user.toUserData());
            }
            jdbcUserRepository.saveAllUsers(userData);
        }
        if(toDoRepository.count()==0){
            List<ToDo> toDos = restClient.findAllTodo();

            toDoRepository.saveAll(toDos);
        }

    }
}
