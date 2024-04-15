package co.id.ajarin.model.dashboard;
import java.util.List;

import co.id.ajarin.entity.CategoryEntity;
import co.id.ajarin.entity.CourseDetailEntity;
import co.id.ajarin.model.OutputRepositoryModel;
import co.id.ajarin.model.account.TeacherModel.Teacher;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class CourseModel {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response extends OutputRepositoryModel {

        public List<Course> courses;
        
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class Course extends OutputRepositoryModel{

        public Course(Long course_id2, Integer course_price2, String course_chapter2, String course_title2,
                String course_description2, String course_level2, String course_image2, Integer total_course_sold,
                CategoryEntity category2, List<CourseDetailEntity> course_details2, Teacher teacher2) {
                this.course_id = course_id2;
                this.course_price = course_price2;
                this.course_chapter = course_chapter2;
                this.course_title = course_title2;
                this.course_description = course_description2;
                this.course_level = course_level2;
                this.course_image = course_image2;
                this.total_sold_course = total_course_sold;
                this.category = category2;
                this.course_details = course_details2;
                this.teacher = teacher2;
        }
        
        private Long course_id;
        private Integer course_price;
        private String course_chapter;
        private String course_title;
        private String course_description;
        private String course_level;
        private String course_image;
        private Integer total_sold_course;
        private CategoryEntity category;
        private List<CourseDetailEntity> course_details;
        private Teacher teacher;
    }

}
