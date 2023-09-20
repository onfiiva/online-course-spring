package com.example.springmodels.controllers;

import com.example.springmodels.models.modelUser;
import com.example.springmodels.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class userController {

    @Autowired
    private UserRepository userRepository;


    @GetMapping("/employee")
    public String employeeMain(Model model)
    {
        /*List<modelUser> users = userRepository.findAll();
        model.addAttribute("users", users);*/
        return "employee-main";
    }
}
