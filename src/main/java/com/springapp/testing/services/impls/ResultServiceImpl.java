package com.springapp.testing.services.impls;


import com.springapp.testing.dao.ResultDao;
import com.springapp.testing.model.Answers;
import com.springapp.testing.model.Result;
import com.springapp.testing.model.Test;
import com.springapp.testing.model.User;
import com.springapp.testing.services.ResultService;
import com.springapp.testing.services.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@Service
public class ResultServiceImpl implements ResultService {

    private ResultDao resultDao;
    private TestService testService;

    public void setResultDao(ResultDao resultDao) {
        this.resultDao = resultDao;
    }

    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @Override
    @Transactional
    public void addResult(Result result) {
        this.resultDao.addResult(result);
    }

    @Override
    @Transactional
    public void updateResult(Result result) {
        this.resultDao.updateResult(result);
    }

    @Override
    @Transactional
    public Result findResultForUserById(Result result) {
        return this.resultDao.findResultForUserById(result);
    }

    @Override
    @Transactional
    public List<Result> findAllResultsForUser(User user) {
        return this.resultDao.findAllResultsForUser(user);
    }

    @Override
    @Transactional
    public Result parseResult(Result result, HttpServletRequest request) {
        Enumeration<String> en = request.getParameterNames();
        Test test = testService.findTestById(result.getTest_id());
        Map<String, Answers> map = test.getQuestionAnswersMap();
        List<Answers> answers = new ArrayList<>();
        for (Map.Entry<String, Answers> entry : map.entrySet()) {
            answers.add(entry.getValue());
        }
        int count = 0;
        int rightAnswersCount = 0;
        while (en.hasMoreElements()) {
            String param = en.nextElement();
            if (param.equals("test_id") || param.equals("testSubject") || param.equals("difficulty") || param.equals("user.id")) {
                continue;
            }
            String value = request.getParameter(param);
            if (answers.get(count).getRightAnswer() == Integer.valueOf(value)) {
                rightAnswersCount++;
            }
            count++;
        }
        double res = (100 * rightAnswersCount) / count;
        double newResult = new BigDecimal(res).setScale(2, RoundingMode.UP).doubleValue();
        String percentResult = String.valueOf(newResult) + "%";
        result.setResult(percentResult);
        return result;
    }

    @Override
    @Transactional
    public Result checkExistResult(Result result) {
        Result newResult = this.resultDao.findResultForUserById(result);
        if (newResult != null) {
            return newResult;
        }
        return null;
    }
}
