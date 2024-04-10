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
public class RateCourseModel extends OutputRepositoryModel {
    private String userid;
    private Long courseid;
    private Float rating;
    private String comment;
}
