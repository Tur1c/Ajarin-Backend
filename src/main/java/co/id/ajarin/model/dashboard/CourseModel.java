package co.id.ajarin.model.dashboard;
import java.util.List;

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
        private Long courseId;
        private Integer coursePrice;
        private String courseChapter;
        private String courseTitle;
        private String courseDescription;
        private String courseCategory;
    }

}
