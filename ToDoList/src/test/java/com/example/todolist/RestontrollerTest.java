package com.example.todolist;

import com.example.todolist.todo.ToDo;
import com.example.todolist.todo.ToDoRepository;
import com.example.todolist.user.JdbcUserRepository;
import com.example.todolist.user.UserData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//permet de faire des tests avec mockMVC (utile pour tester des controllers)
@WebMvcTest(controllers = Restontroller.class)
class RestontrollerTest {
    @Autowired
    ObjectMapper  objectMapper;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    JdbcUserRepository userRepository;

    @MockBean
    ToDoRepository toDoRepository;

    List<UserData> users = new ArrayList<>();
    List<ToDo> toDos = new ArrayList<>();

    @BeforeEach
    void setUp() {
        users.add(new UserData(1,
                "Leanne",
                "lgraham",
                "lgraham@gmail.com",
                "1-770-736-8031 x56442",
                "hildegard.org"));
        users.add(new UserData(2,
                "Leo",
                "lgraham",
                "leoham@gmail.com",
                "1-770-736-8031 x58542",
                "hildegardleo.org"));

        toDos.add(new ToDo(1,
                1,
                "Faire une app",
                false,
                0));
        toDos.add(new ToDo(1,
                2,
                "Faire une 2e app",
                false,
                0));
    }

    @Test
    void shouldFindAllUser() throws Exception {
        //modifie le fonctionnement de la fonction pour le test
        when(userRepository.findAll()).thenReturn(users);

        //Test
        mockMvc.perform(get("/api/user"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(users.size())));
    }

    @Test
    void shouldFindUserById() throws Exception {
        UserData user = users.get(0);

        //modifie le fonctionnement de la fonction pour le test
        when(userRepository.findById(1)).thenReturn(Optional.of(user));

        //Test
        mockMvc.perform(get("/api/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(user.id())))
                .andExpect(jsonPath("$.name", is(user.name())))
                .andExpect(jsonPath("$.username", is(user.username())))
                .andExpect(jsonPath("$.website", is(user.website())));
    }

    @Test
    void shouldCreateUser() throws Exception {
        UserData user3 = new UserData(3,
                "Lucas",
                "lgraham",
                "lucaham@gmail.com",
                "1-770-736-8031 x58882",
                "hildegardlucas.org"
        );

        //Test
        mockMvc.perform(post("/api/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user3))
        )
                .andExpect(status().isCreated());

    }

    @Test
    void shouldUpdateUser() throws Exception {
        UserData user3 = new UserData(1,
                "Lucas",
                "lgraham",
                "lucaham@gmail.com",
                "1-770-736-8031 x58882",
                "hildegardlucas.org"
        );

        //Test
        mockMvc.perform(put("/api/user/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user3))
        ).andExpect(status().isNoContent());

    }

    @Test
    void shouldDeleteUser() throws Exception {
        UserData user3 = new UserData(1,
                "Lucas",
                "lgraham",
                "lucaham@gmail.com",
                "1-770-736-8031 x58882",
                "hildegardlucas.org"
        );

        //Test
        mockMvc.perform(delete("/api/user/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void shouldFindAllToDo() throws Exception {
        //modifie le fonctionnement de la fonction pour le test
        when(toDoRepository.findAll()).thenReturn(toDos);

        //Test
        mockMvc.perform(get("/api/toDo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()",is(toDos.size())));
    }

    @Test
    void shouldFindToDoById() throws Exception {
        ToDo todo1 = toDos.get(0);

        //modifie le fonctionnement de la fonction pour le test
        when(toDoRepository.findById(1)).thenReturn(Optional.of(todo1));

        //Test
        mockMvc.perform(get("/api/toDo/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title",is(todo1.title())))
                .andExpect(jsonPath("$.id",is(todo1.id())))
                .andExpect(jsonPath("$.userId",is(todo1.userId())));
    }

    @Test
    void shouldCreateToDo() throws Exception {
        ToDo todo = new ToDo(1,3,"Faire une app",false,0);

        //Test
        mockMvc.perform(post("/api/toDo")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(todo)))
                .andExpect(status().isCreated());

    }

    @Test
    void updateToDo() throws Exception {
        ToDo todo = new ToDo(1,1,"Faire une app",false,0);

        //Test
        mockMvc.perform(put("/api/toDo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(todo)))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteToDo() throws Exception {
        //Test
        mockMvc.perform(delete("/api/toDo/1"))
                .andExpect(status().isNoContent());
    }
}