package co.id.ajarin.model.dashboard;

import co.id.ajarin.model.OutputRepositoryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseModel extends OutputRepositoryModel {
    private CourseModel.Course course;
    private String status;
    private String completed_chap;
    private Float rating;
    private String comment;
}
