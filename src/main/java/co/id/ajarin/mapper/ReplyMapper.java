package co.id.ajarin.mapper;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.ForumEntity;
import co.id.ajarin.entity.ForumReplyEntity;
import co.id.ajarin.model.forum.ReplyModel;

public class ReplyMapper {
    public static ReplyModel.Reply maptoReplyModel(ForumReplyEntity reply){
        AccountRegisterEntity acc = reply.getUser();
        acc = new AccountRegisterEntity(acc.getId(), acc.getFirstName(), acc.getLastName(), acc.getEmail(), acc.getPassword(), acc.getRole(), acc.getGender(), acc.getCity(), acc.getCountry(), acc.getSchool(), acc.getAge(), acc.getPhoneNumber(), acc.getEducation(), acc.getCoin(), acc.getPic_name(), acc.getPic_type(), acc.getData());

        // ForumEntity forum = new ForumEntity()

        return new ReplyModel.Reply(
            reply.getFr_id(),
            reply.getFr_reply(),
            reply.getFr_likes(),
            acc,
            // reply.getForum(),
            reply.getFr_replied_at()
        );
    }
}
