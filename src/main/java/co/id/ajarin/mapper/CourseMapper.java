package co.id.ajarin.mapper;


import co.id.ajarin.entity.CourseEntity;
import co.id.ajarin.model.dashboard.CourseModel;

public class CourseMapper {
    public static CourseModel.Course mapToCourseModel(CourseEntity course){
        return new CourseModel.Course(
            course.getCourseid(),
            course.getCourseprice(),
            course.getCoursechapter(),
            course.getCoursetitle(),
            course.getCoursedescription(),
            course.getCoursecategory()
        );
    }

    public static CourseEntity mapToCourseEntity(CourseModel.Course courseModel){
        return new CourseEntity(
            courseModel.getCourseid(),
            courseModel.getCourseprice(),
            courseModel.getCoursechapter(),
            courseModel.getCoursetitle(),
            courseModel.getCoursedescription(),
            courseModel.getCoursecategory()
        );
    }
}
