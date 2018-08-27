package com.springapp.testing.utils;

import com.springapp.testing.model.Test;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CheckBoxValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Test.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Test test = (Test)o;
        String difficulty = test.getDifficulty();
        System.out.println(test);
        if (difficulty.equals("Easy") && test.getRightAnswers().size() != 5){
            errors.rejectValue("rightAnswers", "errors.checkbox");
        }

        if (difficulty.equals("Middle") && test.getRightAnswers().size() != 7){
            errors.rejectValue("rightAnswers", "errors.checkbox");
        }

        if (difficulty.equals("Hard") && test.getRightAnswers().size() != 10){
            errors.rejectValue("rightAnswers", "errors.checkbox");
        }
    }
}
