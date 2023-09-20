package com.example.springmodels.controllers;

import com.example.springmodels.models.UserRoles;
import com.example.springmodels.models.modelUser;
import com.example.springmodels.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

@Controller
public class loginController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    private String home() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    private String LogView()
    {
        return "login";
    }

    @PostMapping("/login")
    private String Login(modelUser user, Model model)
    {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrlUser = "http://localhost:8080/u" + user.getUsername() + "/user";
        modelUser user_from_api = restTemplate.getForObject(apiUrlUser, modelUser.class);

        if (user_from_api != null && user_from_api.getPassword().equals(user.getPassword()))
        {
            model.addAttribute("curruser", user_from_api);
            if(!user_from_api.getRole().getName().equals("ADMIN")) {
                return "redirect:/index";
            } else {
                return "redirect:/admin-page";
            }
        }

        model.addAttribute("message", "Пользователь с таким логином не существует");
        return "login";
    }
}
