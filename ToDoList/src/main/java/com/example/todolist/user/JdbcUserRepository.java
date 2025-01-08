package com.example.todolist.user;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcUserRepository {

    private final JdbcClient jdbcClient;

    public JdbcUserRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<UserData> findAll(){
        return jdbcClient.sql("Select * from users")
                .query(UserData.class)
                .list();
    }
    public Optional<UserData> findById(Integer id){
        return jdbcClient.sql("Select * from users WHERE id = ?")
                .param(id)
                .query(UserData.class)
                .optional();
    }

    public void createUsers(UserData user) {
        var updated = jdbcClient.sql("INSERT INTO Users VALUES (?, ?, ?, ?, ?,?)")
                .params(List.of(user.id(),user.name(),user.username(),user.email(),user.phone(),user.website()))
                .update();
        Assert.state(updated==1,"Failed to create user " );
    }
    public void saveAllUsers(List<UserData> users){
        users.stream().forEach(this::createUsers);
    }

    public int countUsers() {
        return jdbcClient.sql("select * from users").query().listOfRows().size();
    }

    public void delete(Integer id) {
        var updated = jdbcClient.sql("delete from users where id = ?")
                .param(id)
                .update();
        Assert.state(updated==1,"Failed to delete user " );
    }

    public void update(UserData user,Integer id) {
        var updated = jdbcClient.sql("update users set name=?,username=?,email=?,phone=?,website=? where id=?")
                .params(List.of(user.name(),user.username(),user.email(),user.phone(),user.website(),id))
                .update();
        Assert.state(updated==1,"Failed to update user " + user.name());
    }


}
