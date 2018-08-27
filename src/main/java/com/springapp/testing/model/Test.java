package com.springapp.testing.model;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "tests")
public class Test extends Model {

    @Column(name = "subject", nullable = false)
    private String subject;

    @Column(name = "difficulty", nullable = false)
    private String difficulty;

    @Column(name = "questions_number", nullable = false)
    private int questionNumber;

    @Min(value = 1, message = "Timer must be greater than 0")
    @Column(name = "timer", nullable = false)
    private int timer;

    @ElementCollection
    @CollectionTable(name="questions", joinColumns=@JoinColumn(name="test_id"))
    @Column(name = "question")
    private List<String> questions;

    @ElementCollection
    @CollectionTable(name="answers", joinColumns=@JoinColumn(name="test_id"))
    @Column(name = "answer")
    private List<String> answers;

    @ElementCollection
    @CollectionTable(name="right_answers", joinColumns=@JoinColumn(name="test_id"))
    @Column(name = "number")
    private List<Integer> rightAnswers;

    @Transient
    private String stringTimer;

    @Transient
    private Map<String, Answers> questionAnswersMap;

    public Test() {
    }

    public Test(String subject, String difficulty) {
        this.subject = subject;
        this.difficulty = difficulty;

        if (difficulty.equals("Easy")) {
            questionNumber = 5;
        } else if (difficulty.equals("Middle")) {
            questionNumber = 7;
        } else if (difficulty.equals("Hard")) {
            questionNumber = 10;
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(int questionNumber) {
        this.questionNumber = questionNumber;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public List<String> getQuestions() {
        return questions;
    }

    public void setQuestions(List<String> questions) {
        this.questions = questions;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public Map<String, Answers> getQuestionAnswersMap() {
        return questionAnswersMap;
    }

    public void setQuestionAnswersMap(Map<String, Answers> questionAnswersMap) {
        this.questionAnswersMap = questionAnswersMap;
    }

    public String getStringTimer() {
        return stringTimer;
    }

    public void setStringTimer(String stringTimer) {
        this.stringTimer = stringTimer;
    }

    public List<Integer> getRightAnswers() {
        return rightAnswers;
    }

    public void setRightAnswers(List<Integer> rightAnswers) {
        this.rightAnswers = rightAnswers;
    }

    @Override
    public String toString() {
        return "Test{" +
                "subject='" + subject + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", questionNumber=" + questionNumber +
                ", timer=" + timer +
                ", questions=" + questions +
                ", answers=" + answers +
                ", rightAnswers=" + rightAnswers +
                ", questionAnswersMap=" + questionAnswersMap +
                '}';
    }
}
