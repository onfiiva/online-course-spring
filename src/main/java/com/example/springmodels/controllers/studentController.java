package com.example.springmodels.controllers;

import com.example.springmodels.models.Concert;
import com.example.springmodels.models.Course;
import com.example.springmodels.models.CourseSummary;
import com.example.springmodels.models.modelUser;
import com.example.springmodels.repos.*;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class studentController {
    private final ConcertRepository concertRepository;
    private final ConcertSongsRepository concertSongsRepository;
    private final CourseDurationRepository courseDurationRepository;
    private final CoursePaymentRepository coursePaymentRepository;
    private final CoursePaymentTypeRepository coursePaymentTypeRepository;
    private final CourseRepository courseRepository;
    private final CourseTypeRepository courseTypeRepository;
    private final StudentSongRepository studentSongRepository;

    private final UniversalRepository universalRepository;

    public studentController(ConcertRepository concertRepository,
                             ConcertSongsRepository concertSongsRepository,
                             CourseDurationRepository courseDurationRepository,
                             CoursePaymentRepository coursePaymentRepository,
                             CoursePaymentTypeRepository coursePaymentTypeRepository,
                             CourseRepository courseRepository,
                             CourseTypeRepository courseTypeRepository,
                             StudentSongRepository studentSongRepository,
                             UniversalRepository universalRepository) {
        this.concertRepository = concertRepository;
        this.concertSongsRepository = concertSongsRepository;
        this.courseDurationRepository = courseDurationRepository;
        this.coursePaymentRepository = coursePaymentRepository;
        this.coursePaymentTypeRepository = coursePaymentTypeRepository;
        this.courseRepository = courseRepository;
        this.courseTypeRepository = courseTypeRepository;
        this.studentSongRepository = studentSongRepository;
        this.universalRepository = universalRepository;
    }

    @GetMapping("/index")
    public String index(Model model) {
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
        return "index";
    }

    @GetMapping("/{modelName}/id/courses")
    public String studentCourses(@PathVariable("modelName") String modelName,
                                 @PathVariable("id") Long id,
                                 Model model
    ) {
        Object modelObject = universalRepository.findEntityById(modelName, id);
        if (modelObject == null) {
            // Обработка ошибки: объект не найден
            return "error";
        }

        model.addAttribute("modelObject", modelObject);
        model.addAttribute("modelName", modelName);

        return "student-courses";

    }
}
