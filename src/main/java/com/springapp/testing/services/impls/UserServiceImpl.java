package com.springapp.testing.services.impls;

import com.springapp.testing.dao.UserDao;
import com.springapp.testing.model.User;
import com.springapp.testing.services.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        this.userDao.addUser(user);
    }

    @Override
    @Transactional
    public User findUserById(int id) {
        return this.userDao.findUserById(id);
    }

    @Override
    @Transactional
    public User findUserByLogin(String login) {
        return this.userDao.findUserByLogin(login);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        this.userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void deleteUserById(int id) {
        this.userDao.deleteUserById(id);
    }

    @Override
    @Transactional
    public List<User> listOfUsers() {
        return this.userDao.listOfUsers();
    }

    @Override
    @Transactional
    public List<User> blockedUsers() {
        return this.userDao.blockedUsers();
    }

    @Override
    @Transactional
    public String validateRegister(User newUser) {
        List<User> users = this.userDao.listOfUsers();
        for (User user : users) {
            if (user.getLogin().equals(newUser.getLogin())) {
                return "That login already exist";
            }
        }
        return null;
    }

    @Override
    @Transactional
    public String validateLogin(User user) {
        User checkUser = this.userDao.findUserByLogin(user.getLogin());
        if (checkUser == null){
            return "Login/password is incorrect";
        }
        if (checkUser.isBlock()) {
            return "You are blocked";
        }
        if (!user.getLogin().equals(checkUser.getLogin()) || !user.getPassword().equals(checkUser.getPassword())) {
            return "Login/password is incorrect";
        }
        return null;
    }
}
