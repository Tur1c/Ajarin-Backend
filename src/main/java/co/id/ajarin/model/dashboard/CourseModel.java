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
        private Long courseid;
        private Integer courseprice;
        private String coursechapter;
        private String coursetitle;
        private String coursedescription;
        private String coursecategory;
    }

}
