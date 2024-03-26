package co.id.ajarin.model.forum;

import java.sql.Timestamp;
import java.util.List;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.ForumEntity;
import co.id.ajarin.entity.ForumReplyEntity;
import co.id.ajarin.model.OutputRepositoryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyModel extends OutputRepositoryModel{

    public ReplyModel(ForumReplyEntity reply) {
        // this.fr_id = reply.getFr_id();
        
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response extends OutputRepositoryModel {
        public List<Reply> replies;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Reply extends OutputRepositoryModel {
        private Long fr_id;
        private String fr_reply;
        private Long fr_likes;
        private AccountRegisterEntity user_id;
        // private ForumEntity forumEntity;
        private Timestamp fr_replied_at;
    }
}
