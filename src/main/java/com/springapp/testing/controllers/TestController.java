package com.springapp.testing.controllers;

import com.springapp.testing.model.Result;
import com.springapp.testing.model.Test;
import com.springapp.testing.services.TestService;
import com.springapp.testing.utils.CheckBoxValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class TestController {

    private TestService testService;
    private CheckBoxValidator checkBoxValidator;

    @Autowired
    @Qualifier(value = "checkBoxValidator")
    public void setCheckBoxValidator(CheckBoxValidator checkBoxValidator) {
        this.checkBoxValidator = checkBoxValidator;
    }

    @Autowired
    @Qualifier(value = "testService")
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @InitBinder
    protected void initBinder(final WebDataBinder binder){
        binder.addValidators(checkBoxValidator);
    }

    @RequestMapping(value = "/add_test", method = RequestMethod.GET)
    public String addTest(HttpServletRequest request, Model model) {
        String subject = request.getParameter("subject");
        String difficulty = request.getParameter("difficulty");
        Test test = new Test(subject, difficulty);
        model.addAttribute("newTest", test);
        return "add_test";
    }

    @RequestMapping(value = "/all_tests", method = RequestMethod.GET)
    public ModelAndView showAllTests() {
        List<Test> testList = testService.findAllTests();
        return new ModelAndView("tests", "testList", testList);
    }

    @RequestMapping(value = "/create_test", method = RequestMethod.POST)
    public String createTest(@Valid @ModelAttribute("newTest") Test test, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add_test";
        }
        System.out.println(test);
        testService.addTest(test);
        return "redirect:/all_tests";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String deleteTest(@RequestParam("id") int testId) {
        testService.deleteTestById(testId);
        return "redirect: /all_tests";
    }

    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String showUpdatePage(@RequestParam("id") int testId, Model model) {
        Test test = testService.findTestById(testId);
        model.addAttribute("test", test);
        return "update_test";
    }

    @RequestMapping(value = "/update_test", method = RequestMethod.POST)
    public String updateTest(@ModelAttribute("test") Test test, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "update_test";
        }
        testService.updateTest(test);
        return "redirect: /all_tests";
    }
}
