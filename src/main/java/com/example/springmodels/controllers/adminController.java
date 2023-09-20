package com.example.springmodels.controllers;

import com.example.springmodels.models.Course;
import com.example.springmodels.models.modelUser;
import com.example.springmodels.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class adminController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/admin-page")
    public String userView(Model model)
    {
        RestTemplate restTemplate = new RestTemplate();

        String apiUrlCourses = "http://localhost:8080/courses";
        ResponseEntity<List<Course>> responseEntity = restTemplate.exchange(
                apiUrlCourses,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Course>>() {
                });

        List<Course> courses = responseEntity.getBody();

        model.addAttribute("courses", courses);
        return "admin-page";
    }

    @GetMapping("/show/{modelName}/{id}")
    public String showModel(
            @PathVariable("modelName") String modelName,
            @PathVariable("id") Long id,
            Model model
    ) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrlCourse = "http://localhost:8080/course/"+id;
        Course course_from_api = restTemplate.getForObject(apiUrlCourse, Course.class);

        Object modelObject = course_from_api;
        if (modelObject == null) {
            return "error";
        }

        model.addAttribute("modelObject", modelObject);
        model.addAttribute("modelName", modelName);

        return "admin-show";
    }

    @GetMapping("/edit/{modelName}/{id}")
    public String editModel(
            @PathVariable("modelName") String modelName,
            @PathVariable("id") Long id,
            Model model
    ) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrlCourse = "http://localhost:8080/course/"+id;
        Course course_from_api = restTemplate.getForObject(apiUrlCourse, Course.class);

        model.addAttribute("modelObject", course_from_api);
        model.addAttribute("modelName", modelName);

        return "admin-edit";
    }

    @PostMapping("/edit/{modelName}/{id}")
    public String editModelObject(
            @PathVariable("modelName") String modelName,
            @PathVariable("id") Long id,
            @ModelAttribute("modelObject") Course updatedModelObject
    ) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrlCourse = "http://localhost:8080/course/"+id;
        Course course_from_api = restTemplate.getForObject(apiUrlCourse, Course.class);

        Object existingModelObject = course_from_api;
        if (existingModelObject == null) {
            return "error";
        }

        course_from_api.setName(updatedModelObject.getName());
        course_from_api.setLessonsCount(updatedModelObject.getLessonsCount());
        course_from_api.setCost(updatedModelObject.getCost());
        course_from_api.setImage(updatedModelObject.getImage());

        String apiUrlCoursePut = "http://localhost:8080//coursetype/t"+ course_from_api.getCourseType().getId() + "/courseduration/d" + course_from_api.getCourseDuration().getId() + "/coursepayment/p" + course_from_api.getCoursePayment().getId() + "/course/c" + course_from_api.getId();
        restTemplate.put(apiUrlCoursePut, course_from_api, Course.class);

        // После редактирования элемента, перенаправьте пользователя обратно на главную страницу
        return "redirect:/admin-page";
    }

    @PostMapping("/delete")
    public String deleteModelObject(
            @RequestParam(name="modelName") String modelName,
            @RequestParam(name="id") Long id
    ) {
        RestTemplate restTemplate = new RestTemplate();
        String apiUrlCourse = "http://localhost:8080/course/"+id;
        Course course_from_api = restTemplate.getForObject(apiUrlCourse, Course.class);

        String apiUrlCourseDelete = "http://localhost:8080//coursetype/t"+ course_from_api.getCourseType().getId() + "/courseduration/d" + course_from_api.getCourseDuration().getId() + "/coursepayment/p" + course_from_api.getCoursePayment().getId() + "/course/c" + course_from_api.getId();
        restTemplate.delete(apiUrlCourseDelete, course_from_api, Course.class);

        // После удаления элемента, перенаправьте пользователя обратно на главную страницу
        return "redirect:/admin-page";
    }
}
