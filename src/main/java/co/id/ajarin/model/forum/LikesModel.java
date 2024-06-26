package co.id.ajarin.model.forum;

import java.util.List;

import co.id.ajarin.model.OutputRepositoryModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LikesModel extends OutputRepositoryModel{
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response extends OutputRepositoryModel {
        public List<Likes> likes;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Likes extends OutputRepositoryModel {
        private Long likes_id;
        private Long fr_id;
        private String email;
    }
}
