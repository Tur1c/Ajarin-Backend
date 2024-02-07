package co.id.ajarin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.id.ajarin.model.dashboard.CourseModel;
import co.id.ajarin.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private CourseService courseService;
    
    //Get All Course
    @GetMapping
    public ResponseEntity<List<CourseModel>> getAllCourse(){
        List<CourseModel> courses = courseService.getAllCourse();

        return ResponseEntity.ok(courses);
    }
}
