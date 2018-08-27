package com.springapp.testing.controllers;


import com.springapp.testing.model.Result;
import com.springapp.testing.model.Test;
import com.springapp.testing.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ResultController {

    private ResultService resultService;

    @Autowired
    @Qualifier(value = "resultService")
    public void setResultService(ResultService resultService) {
        this.resultService = resultService;
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String showResult(@ModelAttribute("result") Result result, HttpServletRequest request, Model model) {
        Result newResult = resultService.checkExistResult(result);
        if (newResult != null) {
            newResult.setResult(result.getResult());
            newResult.setTest_id(result.getTest_id());
            newResult = resultService.parseResult(newResult, request);
            resultService.updateResult(newResult);
            model.addAttribute("result", newResult);
            return "result";
        } else {
            result = resultService.parseResult(result, request);
            resultService.addResult(result);
            model.addAttribute("result", result);
            return "result";
        }
    }
}
