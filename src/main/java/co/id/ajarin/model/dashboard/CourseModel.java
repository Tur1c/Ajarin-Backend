package co.id.ajarin.model.dashboard;
import java.sql.Date;
import java.sql.Time;

import co.id.ajarin.model.OutputRepositoryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseModel extends OutputRepositoryModel{
    private Long id;
    private String CourseName;
    private int price;
    private Time CourseTime;
    private Date CourseDate;
    private String description;
    private String CourseType;
    private String Category;
}
