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
public class DiscussionModel {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response extends OutputRepositoryModel {
        public List<Discussion> discussions;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Discussion {
        private Long discId;
        private String discTitle;
        private String discParticipant;
        private Time discTime;
        private Date discDate;
        private String discDescription;
        private String discCategory;
    }
}
