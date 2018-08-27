package com.springapp.testing.controllers;

import com.springapp.testing.model.Result;
import com.springapp.testing.model.Test;
import com.springapp.testing.model.User;
import com.springapp.testing.services.ResultService;
import com.springapp.testing.services.TestService;
import com.springapp.testing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class UserController {

    private UserService userService;
    private ResultService resultService;
    private TestService testService;

    @Autowired
    @Qualifier(value = "resultService")
    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }

    @Autowired
    @Qualifier(value = "userService")
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    @Qualifier(value = "testService")
    public void setTestService(TestService testService) {
        this.testService = testService;
    }

    @RequestMapping(value = "/my_page", method = RequestMethod.GET)
    public String showMyPage(@SessionAttribute("user") User user, Model model){
        if (user.getRole().getTitle().equals("ADMIN")){
            List<User> blockedUsers = userService.blockedUsers();
            model.addAttribute("blockedUsers", blockedUsers);
        }else {
            List<Result> resultList = resultService.findAllResultsForUser(user);
            model.addAttribute("resultList", resultList);
        }
        return "my_page";
    }

    @RequestMapping(value = "/all_users", method = RequestMethod.GET)
    public String showAllUsers(Model model){
        List<User> userList = userService.listOfUsers();
        model.addAttribute("userList", userList);
        return "all_users";
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    public String startTest(@RequestParam("id") int testId, Model model) {
        Test test = testService.findTestById(testId);
        model.addAttribute("test", test);
        model.addAttribute("result", new Result());
        return "test_page";
    }

    @RequestMapping(value = "/block", method = RequestMethod.GET)
    public String blockUser(@RequestParam("id") int userId){
        User user = userService.findUserById(userId);
        if (user.isBlock()){
            user.setBlock(false);
        }else {
            user.setBlock(true);
        }
        userService.updateUser(user);
        return "redirect: /all_users";
    }
}
