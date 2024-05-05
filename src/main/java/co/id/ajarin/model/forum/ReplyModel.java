package co.id.ajarin.model.forum;

import java.sql.Timestamp;
import java.util.List;

import co.id.ajarin.model.OutputRepositoryModel;
import co.id.ajarin.model.account.AccountModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ReplyModel extends OutputRepositoryModel{

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
        private AccountModel user_id;
        private Timestamp fr_replied_at;
        private List<LikesModel.Likes> likes;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DeleteResponse extends OutputRepositoryModel {
        public List<DeleteReply> replies;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class DeleteReply extends OutputRepositoryModel {
        private Long fr_id;
        private String fr_reply;
        private Long fr_likes;
        private AccountModel fr_user;
        private Timestamp fr_replied_at;
        private List<LikesModel.Likes> likes;
    }
}
