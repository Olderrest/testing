package com.springapp.testing.dao.impls;

import com.springapp.testing.dao.UserDao;
import com.springapp.testing.model.Role;
import com.springapp.testing.model.User;
import com.springapp.testing.services.RoleService;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private static final Logger LOGGER = Logger.getLogger(UserDaoImpl.class);

    private RoleService roleService;
    private SessionFactory sessionFactory;

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        Role role = this.roleService.findRoleById(0);
        user.setRole(role);
        session.save(user);
        LOGGER.info("User successfully saved. User info: " + user);
    }

    @Override
    public void updateUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(user.getRole());
        session.update(user);
        LOGGER.info("User successfully updated. User info: " + user);
    }

    @Override
    public User findUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.get(User.class, new Integer(id));
        if (user != null) {
            LOGGER.info("User successfully found. User info: " + user);
        } else {
            LOGGER.info("User not found");
        }
        return user;
    }

    @Override
    public User findUserByLogin(String login) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from User where login = :login");
        query.setParameter("login", login);
        User user = (User) query.uniqueResult();
        if (user != null) {
            LOGGER.info("User successfully found. User info: " + user);
        } else {
            LOGGER.info("User not found");
        }

        return user;
    }

    @Override
    public void deleteUserById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        User user = (User) session.load(User.class, new Integer(id));
        if (user != null) {
            session.delete(user);
            LOGGER.info("User successfully deleted");
        } else {
            LOGGER.info("User with id " + id + " not found");
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listOfUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("from User").list();
        for (User user : users) {
            LOGGER.info("User list: " + user);
        }
        return users;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> blockedUsers() {
        Session session = this.sessionFactory.getCurrentSession();
        List<User> users = session.createQuery("from User where block=true").list();
        for (User user : users) {
            LOGGER.info("User list: " + user);
        }
        return users;
    }
}
