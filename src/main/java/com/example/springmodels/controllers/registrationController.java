package com.example.springmodels.controllers;

import com.example.springmodels.models.Course;
import com.example.springmodels.models.UserRoles;
import com.example.springmodels.models.modelUser;
import com.example.springmodels.repos.UserRepository;
import com.example.springmodels.repos.UserRolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;

@Controller
public class registrationController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;
    @GetMapping("/registration")
    private String RegView()
    {
        return "regis";
    }
    @PostMapping("/registration")
    private String Reg(modelUser user, Model model)
    {
        RestTemplate restTemplate = new RestTemplate();

        String apiUrlUser = "http://localhost:8080/u" + user.getUsername() + "/user";
        modelUser user_from_api = restTemplate.getForObject(apiUrlUser, modelUser.class);

        if (user_from_api != null)
        {
            model.addAttribute("message", "Пользователь с таким логином уже существует");
            return "regis";
        }

        String apiUrlRole = "http://localhost:8080/userroles";
        ParameterizedTypeReference<List<UserRoles>> responseType = new ParameterizedTypeReference<List<UserRoles>>() {};

        ResponseEntity<List<UserRoles>> responseEntity = restTemplate.exchange(
                apiUrlRole,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<UserRoles>>() {
                });

        List<UserRoles> roles = responseEntity.getBody();

        UserRoles role = new UserRoles();

        for(int i = 0; i < roles.size(); i++){
            if (roles.get(i).getName().equals("STUDENT")) role = roles.get(i);
        }
        user.setRole(role);

        String apiUrlUserPost = "http://localhost:8080/userrole/" + role.getId() + "/users";
        modelUser createdUser = restTemplate.postForObject(apiUrlUserPost, user, modelUser.class);

        return "redirect:/login";
    }
}
