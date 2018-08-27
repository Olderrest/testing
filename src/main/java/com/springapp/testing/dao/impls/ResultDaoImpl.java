package com.springapp.testing.dao.impls;


import com.springapp.testing.dao.ResultDao;
import com.springapp.testing.model.Result;
import com.springapp.testing.model.User;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import java.util.List;

public class ResultDaoImpl implements ResultDao {
    private static final Logger LOGGER = Logger.getLogger(ResultDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addResult(Result result) {
        Session session = this.sessionFactory.getCurrentSession();
        session.save(result);
        LOGGER.info("Result successfully saved. Result info: " + result);
    }

    @Override
    public void updateResult(Result result) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(result);
        LOGGER.info("Result successfully updated. Result info: " + result);
    }

    @Override
    public Result findResultForUserById(Result result) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Result where testSubject = :testSubject and difficulty = :difficulty and user.id = :id");
        query.setParameter("testSubject", result.getTestSubject());
        query.setParameter("difficulty", result.getDifficulty());
        query.setParameter("id", result.getUser().getId());
        Result newResult = (Result) query.uniqueResult();
        return newResult;
    }

    @Override
    public List<Result> findAllResultsForUser(User user) {
        Session session = this.sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Result where user.id = :id");
        query.setParameter("id", user.getId());
        List<Result> results = query.list();
        for (Result result : results) {
            LOGGER.info("Test list: " + result);
        }
        return results;
    }
}
