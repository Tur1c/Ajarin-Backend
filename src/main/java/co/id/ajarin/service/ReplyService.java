package co.id.ajarin.service;

import java.sql.Timestamp;
import java.util.List;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.ForumReplyEntity;
import co.id.ajarin.model.account.AccountRegistrationModel;
import co.id.ajarin.model.forum.ReplyModel;

public interface ReplyService {
    List<ReplyModel.Reply> getAllReply();

    // String save(String reply, Long forum_id, Long user_id, Timestamp timestamp);

    ForumReplyEntity inputReply(String reply, String email, Long forum_id);
}
