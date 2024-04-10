package co.id.ajarin.mapper;



import co.id.ajarin.entity.CourseEntity;
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
            TeacherMapper.mapToTeacherModel(course.getTeacher(),null)
        );
    }

    public static CourseModel.Course mapToCourseModelNoR(CourseEntity course, TeacherModel.Teacher teacher){
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
            teacher
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
    //         courseModel.getTeacher()
            
    //     );
    // }
}
