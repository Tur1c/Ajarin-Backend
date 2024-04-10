package co.id.ajarin.model.dashboard;

import co.id.ajarin.model.OutputRepositoryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JoinDiscCourseModel extends OutputRepositoryModel {
    private String email;
    private Long id;
}
