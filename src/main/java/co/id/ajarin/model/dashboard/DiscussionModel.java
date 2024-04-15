package co.id.ajarin.model.dashboard;

import java.util.Date;
import java.sql.Time;
import java.util.List;

import co.id.ajarin.entity.CategoryEntity;
import co.id.ajarin.model.OutputRepositoryModel;
import co.id.ajarin.model.account.TeacherModel;
import co.id.ajarin.model.account.TeacherModel.Teacher;
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
        public Discussion(Long private_id, String title, int i, Long offered_coin, Time date_start, Time date_end,
                java.sql.Date private_date, String difficulty, String education, Object disc_image2, CategoryEntity subject,
                Teacher teachermapped, long joinedParticipant2) {
            this.disc_id = private_id;
            this.disc_title = title;
            this.disc_participant = "1";
            this.disc_price = offered_coin.intValue();
            this.disc_starttime = date_start;
            this.disc_endtime = date_end;
            this.disc_date = private_date;
            this.disc_description = difficulty;
            this.disc_level = education;
            this.disc_image = null;
            this.category = subject;
            this.teacher = teachermapped;
            this.joinedParticipant = 1L;

        }
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
        private CategoryEntity category;
        private TeacherModel.Teacher teacher;
        private Long joinedParticipant;
    }
}
