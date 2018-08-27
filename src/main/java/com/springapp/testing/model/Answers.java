package com.springapp.testing.model;

import java.util.List;

public class Answers {

    private int rightAnswer;

    private List<String> existingAnswers;

    public int getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(int rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public List<String> getExistingAnswers() {
        return existingAnswers;
    }

    public void setExistingAnswers(List<String> existingAnswers) {
        this.existingAnswers = existingAnswers;
    }

    @Override
    public String toString() {
        return "Answers{" +
                "rightAnswer=" + rightAnswer +
                ", existingAnswers=" + existingAnswers +
                '}';
    }
}
