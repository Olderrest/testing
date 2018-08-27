package com.springapp.testing.controllers;

import com.springapp.testing.model.User;
import com.springapp.testing.services.TestService;
import com.springapp.testing.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@SessionAttributes("user")
public class LoginController {

    private UserService userService;
    private TestService testService;

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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView main() {
        return new ModelAndView("login", "user", new User());
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        String loginError = userService.validateLogin(user);
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new User());
            return "login";
        }
        if (loginError != null) {
            model.addAttribute("loginError", loginError);
            model.addAttribute("user", new User());
            return "login";
        }
        User loggedInUser = userService.findUserByLogin(user.getLogin());
        model.addAttribute("user", loggedInUser);
        model.addAttribute("testList", testService.findAllTests());
        return "redirect:/all_tests";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@Valid @ModelAttribute("user") User user, BindingResult bindingResult, Model model) {
        String registerError = userService.validateRegister(user);
        if (bindingResult.hasErrors()) {
            model.addAttribute("user", new User());
            return "login";
        }
        if (registerError != null) {
            model.addAttribute("registerError", registerError);
            model.addAttribute("user", new User());
            return "login";
        }
        userService.addUser(user);
        model.addAttribute("user", user);
        model.addAttribute("testList", testService.findAllTests());
        return "redirect:/all_tests";
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();
        return "redirect:/";
    }

    @ModelAttribute("user")
    public User getUser() {
        return new User();
    }
}
