package com.example.todolist;

import com.example.todolist.todo.ToDo;
import com.example.todolist.user.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import java.util.List;

//Classe qui recupre des donnes de "https://jsonplaceholder.typicode.com/"
@Component
public class RestClient {
    private final org.springframework.web.client.RestClient restClient;
    public RestClient(org.springframework.web.client.RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build();
    }
    public List<User> findAllUsers() {
        return restClient.get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }

    public User findUserById(Integer id) {
        return restClient.get()
                .uri("/users/{id}",id)
                .retrieve()
                .body(User.class);
    }

    public List<ToDo> findAllTodo(){
        return restClient.get()
                .uri("/todos")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});
    }


}
