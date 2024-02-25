package co.id.ajarin.model.forum;

import java.sql.Timestamp;
import java.util.List;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.CategoryEntity;
import co.id.ajarin.model.OutputRepositoryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class ForumModel {
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response extends OutputRepositoryModel {
        public List<Forum> forums;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Forum {
        private Long question_id;
        private String question_title;
        private String question_desc;
        private String question_level;
        private String question_image;
        private Integer total_comment;
        private Timestamp created_date;
        private CategoryEntity category_id;
        private AccountRegisterEntity user_id;
    }
}
