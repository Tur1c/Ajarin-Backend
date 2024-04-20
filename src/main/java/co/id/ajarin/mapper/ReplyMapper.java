package co.id.ajarin.mapper;

import java.util.ArrayList;
import java.util.List;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.ForumReplyEntity;
import co.id.ajarin.entity.LikesEntity;
import co.id.ajarin.model.account.AccountModel;
import co.id.ajarin.model.forum.LikesModel;
import co.id.ajarin.model.forum.ReplyModel;


public class ReplyMapper {
    public static ReplyModel.Reply maptoReplyModel(ForumReplyEntity reply){
        AccountModel account = UserMapper.mapToAccountModelNoR(reply.getUser());

        
        

        List<LikesModel.Likes> likes_list = new ArrayList<>();

        
        if(!reply.getLikes().isEmpty()){
            for(LikesEntity likes : reply.getLikes()){

            LikesModel.Likes like = LikesMapper.maptoLikesModel(likes);

            likes_list.add(like);
            }
        }

        Long reply_likes = (long) likes_list.size();
        
        // ForumEntity forum = new ForumEntity()

        return new ReplyModel.Reply(
            reply.getFr_id(),
            reply.getFr_reply(),
            reply_likes,
            account,
            // reply.getForum(),
            reply.getFr_replied_at(),
            likes_list
        );
    }

    // public static ReplyModel mapToReplyModel(ReplyModel rep){
    //     AccountModel account = UserMapper.mapToAccountModelNoR(rep.getUser());

    //     return new ReplyModel(
    //         rep.getFr_id(),
    //         rep.getFr_reply(),
    //         rep.getFr_likes(),
    //         account,
    //         rep.getFr_replied_at()
    //     );
    // }
}
