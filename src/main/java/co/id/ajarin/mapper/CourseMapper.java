package co.id.ajarin.mapper;


import co.id.ajarin.entity.CourseEntity;
import co.id.ajarin.model.dashboard.CourseModel;

public class CourseMapper {
    public static CourseModel.Course mapToCourseModel(CourseEntity course){
        return new CourseModel.Course(
            course.getCourse_id(),
            course.getCourse_price(),
            course.getCourse_chapter(),
            course.getCourse_title(),
            course.getCourse_description(),
            course.getCourse_category(),
            course.getCourse_image(),
            course.getCategories()
        );
    }

    public static CourseEntity mapToCourseEntity(CourseModel.Course courseModel){
        return new CourseEntity(
            courseModel.getCourse_id(),
            courseModel.getCourse_price(),
            courseModel.getCourse_chapter(),
            courseModel.getCourse_title(),
            courseModel.getCourse_description(),
            courseModel.getCourse_category(),
            courseModel.getCourse_image(),
            courseModel.getCategories()
        );
    }
}
