package com.springapp.testing.dao.impls;


import com.springapp.testing.dao.TestDao;
import com.springapp.testing.model.Test;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDaoImpl implements TestDao {
    private static final Logger LOGGER = Logger.getLogger(TestDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addTest(Test test) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(test);
        LOGGER.info("Test successfully saved. Test info: " + test);
    }

    @Override
    public void updateTest(Test test) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(test);
        LOGGER.info("Test successfully updated. Test info: " + test);
    }

    @Override
    public Test findTestById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Test test = (Test) session.get(Test.class, new Integer(id));
        if (test != null) {
            LOGGER.info("Test successfully found. User info: " + test);
        } else {
            LOGGER.info("Test not found");
        }
        return test;
    }

    @Override
    public Test findTestBySubjectAndDifficulty(String subject, String difficulty) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Test where subject = :subject and difficulty = :difficulty");
        query.setParameter("subject", subject);
        query.setParameter("difficulty", difficulty);
        Test test = (Test) query.uniqueResult();
        if (test != null) {
            LOGGER.info("Test successfully found. User info: " + test);
        } else {
            LOGGER.info("Test not found");
        }
        return test;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Test> findAllTests() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Test> tests = session.createQuery("from Test").list();
        for (Test test : tests) {
            LOGGER.info("Test list: " + test);
        }
        return tests;
    }

    @Override
    public void deleteTestById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Test test = (Test) session.load(Test.class, new Integer(id));
        if (test != null) {
            session.delete(test);
            LOGGER.info("User successfully deleted");
        } else {
            LOGGER.info("User with id " + id + " not found");
        }
    }
}
