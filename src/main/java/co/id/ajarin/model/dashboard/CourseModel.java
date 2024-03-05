package co.id.ajarin.model.dashboard;
import java.util.List;

import co.id.ajarin.entity.CategoryEntity;
import co.id.ajarin.entity.CourseDetailEntity;
import co.id.ajarin.model.OutputRepositoryModel;
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
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Course {
        private Long course_id;
        private Integer course_price;
        private String course_chapter;
        private String course_title;
        private String course_description;
        private String course_level;
        private String course_image;
        private CategoryEntity category;
        private List<CourseDetailEntity> course_details;
    }

}
