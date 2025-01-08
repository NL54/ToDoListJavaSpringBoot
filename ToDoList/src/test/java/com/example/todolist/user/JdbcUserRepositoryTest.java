package com.example.todolist.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//pour faire des tests sur les bbd gérer avec JDBC, tout les tests sont indépendants
@JdbcTest
//pour import lees @Bean
@Import(JdbcUserRepository.class)
//permet d'utiliser une bdd uniquement en test
//ne pas oublier d'ajouter :
//spring.datasource.url =
//spring.datasource.username =
//spring.datasource.password =
//dans application.properties pour que cela fonctionne correctement (il faut que cela soit coherent avec compose.yml)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class JdbcUserRepositoryTest {
    @Autowired
    JdbcUserRepository jdbcUserRepository;

    @BeforeEach
    void setUp() {
        jdbcUserRepository.createUsers(new UserData(1,
                "Leanne",
                "lgraham",
                "lgraham@gmail.com",
                "1-770-736-8031 x56442",
                "hildegard.org"));
        jdbcUserRepository.createUsers(new UserData(2,
                "Leo",
                "lgraham",
                "leoham@gmail.com",
                "1-770-736-8031 x58542",
                "hildegardleo.org"));
    }

    @Test
    void shouldFindAll() {
        //Test
        List<UserData> runs = jdbcUserRepository.findAll();
        assertEquals(2, runs.size());
    }

    @Test
    void findById() {
        //Test
        Optional<UserData> user = jdbcUserRepository.findById(1);
        assertNotNull(user);
        assertEquals(user.get().name(), "Leanne");
    }

    @Test
    void createUsers() {
        UserData user3 = new UserData(3,
                "Lucas",
                "lgraham",
                "lucaham@gmail.com",
                "1-770-736-8031 x58882",
                "hildegardlucas.org"
        );

        //Test
        jdbcUserRepository.createUsers(user3);
        Optional<UserData> user = jdbcUserRepository.findById(3);
        assertEquals(user.get().name(), "Lucas");
        List<UserData> users = jdbcUserRepository.findAll();
        assertEquals(3,users.size());

    }


    @Test
    void delete() {
        //Test
        jdbcUserRepository.delete(1);
        List<UserData> users = jdbcUserRepository.findAll();
        assertEquals(1, users.size());
    }

    @Test
    void update() {
        UserData user3 = new UserData(1,
                "Lucas",
                "lgraham",
                "lucaham@gmail.com",
                "1-770-736-8031 x58882",
                "hildegardlucas.org"
        );

        //Test
        jdbcUserRepository.update(user3,1);
        Optional<UserData> user = jdbcUserRepository.findById(1);
        assertEquals(user.get().name(), "Lucas");
        assertEquals(user.get().id(), 1);
        assertEquals(user.get().username(), "lgraham");
        assertEquals(user.get().phone(), "1-770-736-8031 x58882");
        assertEquals(user.get().website(), "hildegardlucas.org");

    }
}