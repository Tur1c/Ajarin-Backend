package co.id.ajarin.mapper;


import co.id.ajarin.entity.LikesEntity;
import co.id.ajarin.model.forum.LikesModel;

public class LikesMapper {
    public static LikesModel.Likes maptoLikesModel(LikesEntity likes){
        // AccountModel account = UserMapper.mapToAccountModelNoR(likes.getUser());

        // ReplyModel reply = ReplyMapper.mapToReplyModel(likes.getReply())

        return new LikesModel.Likes(
            likes.getLikes_id(),
            likes.getFr_id(),
            likes.getEmail()
        );
    }
}
