package co.id.ajarin.mapper;

import java.util.ArrayList;
import java.util.List;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.ForumEntity;
import co.id.ajarin.entity.ForumReplyEntity;
import co.id.ajarin.entity.LikesEntity;
import co.id.ajarin.model.account.AccountModel;
import co.id.ajarin.model.forum.LikesModel;
import co.id.ajarin.model.forum.LikesModel.Likes;
import co.id.ajarin.model.forum.ReplyModel;

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
