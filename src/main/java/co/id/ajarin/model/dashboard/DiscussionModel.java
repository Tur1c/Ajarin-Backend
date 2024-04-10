package co.id.ajarin.model.dashboard;

import java.util.Date;
import java.sql.Time;
import java.util.List;

import co.id.ajarin.entity.CategoryEntity;
import co.id.ajarin.model.OutputRepositoryModel;
import co.id.ajarin.model.account.TeacherModel;
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
        private Long disc_id;
        private String disc_title;
        private String disc_participant;
        private Integer disc_price;
        private Time disc_starttime;
        private Time disc_endtime;
        private Date disc_date;
        private String disc_description;
        private String disc_level;
        private String disc_image;
        private String disc_url;
        private CategoryEntity category;
        private TeacherModel.Teacher teacher;
        private Long joinedParticipant;
    }
}
