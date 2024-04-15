package co.id.ajarin.mapper;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.ForumReplyEntity;
import co.id.ajarin.model.account.AccountModel;
import co.id.ajarin.model.forum.ReplyModel;

public class ReplyMapper {
    public static ReplyModel.Reply maptoReplyModel(ForumReplyEntity reply){
        // AccountRegisterEntity acc = reply.getUser();
        // System.out.println(acc + "lewat sini");
        AccountModel account = UserMapper.mapToAccountModelNoR(reply.getUser());

        // ForumEntity forum = new ForumEntity()

        return new ReplyModel.Reply(
            reply.getFr_id(),
            reply.getFr_reply(),
            reply.getFr_likes(),
            account,
            // reply.getForum(),
            reply.getFr_replied_at()
        );
    }
}
