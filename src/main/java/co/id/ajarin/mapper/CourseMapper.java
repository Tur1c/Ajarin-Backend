package co.id.ajarin.mapper;


import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import co.id.ajarin.entity.CourseEntity;
import co.id.ajarin.entity.TeacherEntity;
import co.id.ajarin.model.account.AccountRegistrationModel;
import co.id.ajarin.model.account.TeacherModel;
import co.id.ajarin.model.dashboard.CourseModel;

public class CourseMapper {
    public static CourseModel.Course mapToCourseModel(CourseEntity course){
        return new CourseModel.Course(
            course.getCourse_id(),
            course.getCourse_price(),
            course.getCourse_chapter(),
            course.getCourse_title(),
            course.getCourse_description(),
            course.getCourse_level(),
            course.getCourse_image(),
            course.getTotal_course_sold(),
            course.getCategory(),
            course.getCourse_details(),
            // course.getTeacher()
            new TeacherModel.Teacher(course.getTeacher().getTeacher_id(), course.getTeacher().getProfile_description(), course.getTeacher().getAchievement(), course.getTeacher().getExperience(), course.getTeacher().getEducation(), ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/account/files/cv/").path(course.getTeacher().getTeacher_id().toString()).toUriString(), course.getTeacher().getRating(), new AccountRegistrationModel(course.getTeacher().getUser()))
        );
    }

    // public static CourseEntity mapToCourseEntity(CourseModel.Course courseModel){
    //     return new CourseEntity(
    //         courseModel.getCourse_id(),
    //         courseModel.getCourse_price(),
    //         courseModel.getCourse_chapter(),
    //         courseModel.getCourse_title(),
    //         courseModel.getCourse_description(),
    //         courseModel.getCourse_level(),
    //         courseModel.getCourse_image(),
    //         courseModel.getTotal_sold_course(),
    //         courseModel.getCategory(),
    //         courseModel.getCourse_details(),
    //         new TeacherEntity(courseModel.getTeacher().getId(), courseModel.getTeacher().getProfile_description(), courseModel.getTeacher().getEducation(), courseModel.getTeacher().getExperience(), courseModel.getTeacher().getAchievement(), courseModel.getTeacher().getRating(), courseModel.getTeacher().getCv_data())
            
    //     );
    // }
}
