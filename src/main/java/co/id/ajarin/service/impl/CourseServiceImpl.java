package co.id.ajarin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.id.ajarin.entity.CourseEntity;
import co.id.ajarin.mapper.CourseMapper;
import co.id.ajarin.model.dashboard.CourseModel;
import co.id.ajarin.repository.CourseRepository;
import co.id.ajarin.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService{

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public List<CourseModel.Course> getAllCourse() {
        // TODO Auto-generated method stub
       List<CourseEntity> courses = courseRepository.findAll();
       return courses.stream().map( (course) -> CourseMapper.mapToCourseModel(course)).collect(Collectors.toList());
    }

}
