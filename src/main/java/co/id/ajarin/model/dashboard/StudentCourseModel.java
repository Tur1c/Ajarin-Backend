package co.id.ajarin.model.dashboard;

import java.util.Date;

import co.id.ajarin.model.OutputRepositoryModel;
import co.id.ajarin.model.account.AccountModel;
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
    private Date joined_date;
    private AccountModel account;
}
