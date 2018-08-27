package com.springapp.testing.dao;


import com.springapp.testing.model.Test;

import java.util.List;

public interface TestDao {

    void addTest(Test test);

    void updateTest(Test test);

    Test findTestById(int id);

    Test findTestBySubjectAndDifficulty(String subject, String difficulty);

    List<Test> findAllTests();

    void deleteTestById(int id);
}
