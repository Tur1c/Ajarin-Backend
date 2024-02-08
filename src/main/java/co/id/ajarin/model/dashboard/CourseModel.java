package co.id.ajarin.model.dashboard;
import java.sql.Date;
import java.sql.Time;
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
        private Long id;
        private String CourseName;
        private int price;
        private Time CourseTime;
        private Date CourseDate;
        private String description;
        private String CourseType;
        private String Category;
    }

}
