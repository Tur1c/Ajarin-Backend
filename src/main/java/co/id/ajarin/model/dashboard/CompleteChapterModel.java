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
public class CompleteChapterModel extends OutputRepositoryModel {
    private String completed;
    private Long userid;
    private Long courseid;
    private Long total_chap;
}
