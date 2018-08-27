package com.springapp.testing.services.impls;

import com.springapp.testing.dao.TestDao;
import com.springapp.testing.model.Answers;
import com.springapp.testing.model.Test;
import com.springapp.testing.services.TestService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TestServiceImpl implements TestService {

    private TestDao testDao;

    public void setTestDao(TestDao testDao) {
        this.testDao = testDao;
    }

    @Override
    @Transactional
    public void addTest(Test test) {
        Test newTest = processTest(test);
        this.testDao.addTest(newTest);
    }

    @Override
    @Transactional
    public void updateTest(Test test) {
        Test updateTest = processTest(test);
        this.testDao.updateTest(updateTest);
    }

    @Override
    @Transactional
    public Test findTestById(int id) {
        Test test = this.testDao.findTestById(id);
        String stringTimer = parseTestTimer(test);
        test.setStringTimer(stringTimer);
        return processTest(test);
    }

    @Override
    @Transactional
    public Test findTestBySubjectAndDifficulty(String subject, String difficulty) {
        Test test = this.testDao.findTestBySubjectAndDifficulty(subject, difficulty);
        String stringTimer = parseTestTimer(test);
        test.setStringTimer(stringTimer);
        return processTest(test);
    }

    @Override
    @Transactional
    public List<Test> findAllTests() {
        List<Test> tests = this.testDao.findAllTests();
        return testProcessing(tests);
    }

    @Override
    @Transactional
    public void deleteTestById(int id) {
        this.testDao.deleteTestById(id);
    }

    @Override
    @Transactional
    public Test processTest(Test test) {
        Map<String, Answers> map = new LinkedHashMap<>();
        int count = 0;
        for (int i = 0; i < test.getAnswers().size(); i = i + 4) {
            List<String> listForAnswers = new ArrayList<>();
            listForAnswers.add(test.getAnswers().get(i));
            listForAnswers.add(test.getAnswers().get(i + 1));
            listForAnswers.add(test.getAnswers().get(i + 2));
            listForAnswers.add(test.getAnswers().get(i + 3));
            Answers answers = new Answers();
            answers.setRightAnswer(test.getRightAnswers().get(count));
            answers.setExistingAnswers(listForAnswers);
            map.put(test.getQuestions().get(count), answers);
            count++;
        }
        test.setQuestionAnswersMap(map);
        return test;
    }

    private List<Test> testProcessing(List<Test> tests) {
        List<Test> processedTests = new ArrayList<>();
        for (Test test : tests) {
            Test processedTest = processTest(test);
            processedTests.add(processedTest);
        }
        return processedTests;
    }

    private String parseTestTimer(Test test) {
        String timer = String.valueOf(test.getTimer());
        StringBuilder sb = new StringBuilder("00:");
        if (timer.length() == 1){
            sb.append("0"+timer).append(":00");
        }else {
            sb.append(timer).append(":00");
        }
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        Date date = null;
        try {
            date = sdf.parse(sb.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        StringBuilder newTimer = new StringBuilder();
        String min = String.valueOf(calendar.get(Calendar.MINUTE));
        if (min.length() == 1){
            newTimer.append("0" + calendar.get(Calendar.HOUR) + ":").append("0" + min + ":").append(calendar.get(Calendar.SECOND) + "0");
        }else {
            newTimer.append("0" + calendar.get(Calendar.HOUR) + ":").append(min + ":").append(calendar.get(Calendar.SECOND) + "0");
        }
        return newTimer.toString();
    }
}
