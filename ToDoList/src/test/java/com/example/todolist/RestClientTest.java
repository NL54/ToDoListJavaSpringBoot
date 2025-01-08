package com.example.todolist;

import com.example.todolist.todo.ToDo;
import com.example.todolist.user.Address;
import com.example.todolist.user.Company;
import com.example.todolist.user.Geo;
import com.example.todolist.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.List;

//Annotation pour faire des tests avec des RestClients et utiliser MockRestServer pour modifier
// le fonctonnement des requetes faites à l'API
@org.springframework.boot.test.autoconfigure.web.client.RestClientTest(RestClient.class)
class RestClientTest {
    @Autowired
    MockRestServiceServer server;

    @Autowired
    RestClient restClient;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void shoudFindAllUsers() throws JsonProcessingException {
        User user1 = new User(1,
                "Leanne",
                "lgraham",
                "lgraham@gmail.com",
                new Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", new Geo("-37.3159", "81.1496")),
                "1-770-736-8031 x56442",
                "hildegard.org",
                new Company("Romaguera-Crona", "Multi-layered client-server neural-net", "harness real-time e-markets"));

        List<User> users = List.of(user1);

        //modifie le fonctionnement de la requete pour le test
        //Quand on l'appelle l'API, la fonction findAll() doit etre appele, on renvoie users sous forme de JSON pour le test
        this.server.expect(requestTo("https://jsonplaceholder.typicode.com/users"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(users), MediaType.APPLICATION_JSON));

        //Test
        List<User> allUsers = restClient.findAllUsers();
        assertEquals(allUsers,users);

    }

    @Test
    void shoudFindUserById() throws JsonProcessingException {
        User user = new User(1,
                "Leanne",
                "lgraham",
                "lgraham@gmail.com",
                new Address("Kulas Light", "Apt. 556", "Gwenborough", "92998-3874", new Geo("-37.3159", "81.1496")),
                "1-770-736-8031 x56442",
                "hildegard.org",
                new Company("Romaguera-Crona", "Multi-layered client-server neural-net", "harness real-time e-markets"));

        //modifie le fonctionnement de la requete pour le test
        //Quand on l'appelle l'API, la fonction findUserById() doit etre appele, on renvoie user sous forme de JSON pour le test
        this.server.expect(requestTo("https://jsonplaceholder.typicode.com/users/1"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(user), MediaType.APPLICATION_JSON));

        //Test
        User user1 = restClient.findUserById(1);
        assertEquals(user.getName(), "Leanne", "User name should be Leanne");
        assertEquals(user.getUsername(), "lgraham", "User username should be lgraham");
        assertEquals(user.getEmail(), "lgraham@gmail.com");
        assertAll("Address",
                () -> assertEquals(user.getAddress().street(), "Kulas Light"),
                () -> assertEquals(user.getAddress().suite(), "Apt. 556"),
                () -> assertEquals(user.getAddress().city(), "Gwenborough"),
                () -> assertEquals(user.getAddress().zipcode(), "92998-3874"),
                () -> assertEquals(user.getAddress().geo().lat(), "-37.3159"),
                () -> assertEquals(user.getAddress().geo().lng(), "81.1496")
        );
        assertEquals(user.getPhone(), "1-770-736-8031 x56442");
        assertEquals(user.getWebsite(), "hildegard.org");
        assertAll("Company",
                () -> assertEquals(user.getCompany().name(), "Romaguera-Crona"),
                () -> assertEquals(user.getCompany().catchPhrase(), "Multi-layered client-server neural-net"),
                () -> assertEquals(user.getCompany().bs(), "harness real-time e-markets"));
    }


    @Test
    void findAllTodo() throws JsonProcessingException {
        ToDo todo = new ToDo(1,1,"Faire une app",false,0);
        List<ToDo> toDos = List.of(todo);

        //modifie le fonctionnement de la requete pour le test
        this.server.expect(requestTo("https://jsonplaceholder.typicode.com/todos"))
                .andRespond(withSuccess(objectMapper.writeValueAsString(toDos), MediaType.APPLICATION_JSON));

        //Test
        List<ToDo> allToDos = restClient.findAllTodo();
        assertEquals(allToDos,toDos);
    }
}