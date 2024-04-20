package co.id.ajarin.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.ForumEntity;
import co.id.ajarin.entity.ForumReplyEntity;
import co.id.ajarin.model.account.AccountRegistrationModel;
import co.id.ajarin.model.forum.ForumModel;
import co.id.ajarin.model.forum.ReplyModel;

public interface ReplyService {
    List<ReplyModel.Reply> getAllReply();

    ForumModel.Forum findForumRelated(Long question_id);

    ReplyModel.Reply findById(Long fr_id);

    ForumReplyEntity update(ReplyModel.Reply reply);

    ForumEntity deleteReply(Long question_id, Long fr_id);

    // String save(String reply, Long forum_id, Long user_id, Timestamp timestamp);

    ReplyModel.Reply inputReply(String reply, String email, Long forum_id);
}
