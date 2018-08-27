package com.springapp.testing.dao;

import com.springapp.testing.model.Result;
import com.springapp.testing.model.User;

import java.util.List;

public interface ResultDao {

    void addResult(Result result);

    void updateResult(Result result);

    Result findResultForUserById(Result result);

    List<Result> findAllResultsForUser(User user);
}
