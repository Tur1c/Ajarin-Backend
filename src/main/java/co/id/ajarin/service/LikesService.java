package co.id.ajarin.service;

import co.id.ajarin.entity.AccountRegisterEntity;
import co.id.ajarin.entity.ForumReplyEntity;
import co.id.ajarin.entity.LikesEntity;
import co.id.ajarin.model.forum.ForumModel;

public interface LikesService {
    ForumReplyEntity likeReply(Long fr_id, String email);
    
    String newLikes(Long fr_id, String email);

    ForumModel.Forum findForumRelated(Long question_id);

    String deleteLikes(Long fr_id, String email);
}
