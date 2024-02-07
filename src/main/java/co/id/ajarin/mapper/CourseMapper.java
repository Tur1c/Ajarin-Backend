package co.id.ajarin.mapper;


import co.id.ajarin.entity.CourseEntity;
import co.id.ajarin.model.dashboard.CourseModel;

public class CourseMapper {
    public static CourseModel mapToCourseModel(CourseEntity course){
        return new CourseModel(
            course.getCourseid(),
            course.getCoursename(),
            course.getPrice(),
            course.getCoursetime(),
            course.getCoursedate(),
            course.getDescription(),
            course.getCoursetype(),
            course.getCategoryid()
        );
    }

    public static CourseEntity mapToCourseEntity(CourseModel courseModel){
        return new CourseEntity(
            courseModel.getId(),
            courseModel.getCourseName(),
            courseModel.getPrice(),
            courseModel.getCourseTime(),
            courseModel.getCourseDate(),
            courseModel.getDescription(),
            courseModel.getCourseType(),
            courseModel.getCategory()
        );
    }
}
