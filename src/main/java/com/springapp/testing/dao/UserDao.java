package com.springapp.testing.dao;


import com.springapp.testing.model.User;

import java.util.List;

public interface UserDao {

    void addUser(User user);

    User findUserById(int id);

    User findUserByLogin(String login);

    void updateUser(User user);

    void deleteUserById(int id);

    List<User> listOfUsers();

    List<User> blockedUsers();
}
