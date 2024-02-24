package co.id.ajarin.model.dashboard;

import co.id.ajarin.model.OutputRepositoryModel;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDiscModel extends OutputRepositoryModel{
    private String email;
    private Long id;
}
