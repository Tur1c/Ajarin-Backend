package co.id.ajarin.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import co.id.ajarin.model.dashboard.CourseModel;


public interface CourseService {
    List<CourseModel.Course> getAllCourse();
}
