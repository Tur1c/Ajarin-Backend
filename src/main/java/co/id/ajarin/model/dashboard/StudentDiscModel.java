package co.id.ajarin.model.dashboard;

import java.util.Date;

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
    private DiscussionModel.Discussion discussion;
    private String status;
    private Date joined_date;
}

