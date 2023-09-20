/*
package com.example.springmodels.controllers;

import com.example.springmodels.repos.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    private final ConcertRepository concertRepository;
    private final ConcertSongsRepository concertSongsRepository;
    private final CourseDurationRepository courseDurationRepository;
    private final CoursePaymentRepository coursePaymentRepository;
    private final CoursePaymentTypeRepository coursePaymentTypeRepository;
    private final CourseRepository courseRepository;
    private final CourseTypeRepository courseTypeRepository;
    private final StudentRepository studentRepository;
    private final StudentSongRepository studentSongRepository;

    public MainController(ConcertRepository concertRepository,
                          ConcertSongsRepository concertSongsRepository,
                          CourseDurationRepository courseDurationRepository,
                          CoursePaymentRepository coursePaymentRepository,
                          CoursePaymentTypeRepository coursePaymentTypeRepository,
                          CourseRepository courseRepository,
                          CourseTypeRepository courseTypeRepository,
                          StudentRepository studentRepository,
                          StudentSongRepository studentSongRepository) {
        this.concertRepository = concertRepository;
        this.concertSongsRepository = concertSongsRepository;
        this.courseDurationRepository = courseDurationRepository;
        this.coursePaymentRepository = coursePaymentRepository;
        this.coursePaymentTypeRepository = coursePaymentTypeRepository;
        this.courseRepository = courseRepository;
        this.courseTypeRepository = courseTypeRepository;
        this.studentRepository = studentRepository;
        this.studentSongRepository = studentSongRepository;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("concerts", concertRepository.findAll());
        model.addAttribute("concertsongs", concertSongsRepository.findAll());
        model.addAttribute("coursedurations", courseDurationRepository.findAll());
        model.addAttribute("coursepayments", coursePaymentRepository.findAll());
        model.addAttribute("coursepaymenttypes", coursePaymentTypeRepository.findAll());
        model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("coursetypes", courseTypeRepository.findAll());
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("studentsongs", studentSongRepository.findAll());
        return "index";
    }
}
*/
