package com.springapp.testing.services;


import com.springapp.testing.model.Result;
import com.springapp.testing.model.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ResultService {

    void addResult(Result result);

    void updateResult(Result result);

    List<Result> findAllResultsForUser(User user);

    Result findResultForUserById(Result result);

    Result parseResult(Result result, HttpServletRequest request);

    Result checkExistResult(Result result);
}
