package com.springapp.testing.services;


import com.springapp.testing.model.User;

import java.util.List;

public interface UserService {

    void addUser(User user);

    User findUserById(int id);

    User findUserByLogin(String login);

    void updateUser(User user);

    void deleteUserById(int id);

    List<User> listOfUsers();

    List<User> blockedUsers();

    String validateRegister(User newUser);

    String validateLogin(User user);
}
