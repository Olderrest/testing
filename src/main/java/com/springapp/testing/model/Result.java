package com.springapp.testing.model;

import javax.persistence.*;

@Entity
@Table(name = "results")
public class Result extends Model{
    @Column(name = "test_subject", nullable = false)
    private String testSubject;

    @Column(name = "difficulty", nullable = false)
    private String difficulty;

    @Column(name = "result", nullable = false)
    private String result;

    @Column(name = "test_id", nullable = false)
    private int test_id;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    public String getTestSubject() {
        return testSubject;
    }

    public void setTestSubject(String testSubject) {
        this.testSubject = testSubject;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getTest_id() {
        return test_id;
    }

    public void setTest_id(int test_id) {
        this.test_id = test_id;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + getId() + "\'" +
                "testSubject='" + testSubject + '\'' +
                ", difficulty='" + difficulty + '\'' +
                ", result='" + result + '\'' +
                "test_id=" + test_id + "\'" +
                '}';
    }
}
