package co.id.ajarin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import co.id.ajarin.entity.ForumEntity;
import co.id.ajarin.model.forum.ForumModel;

public interface ForumService {
    List<ForumModel.Forum> getAllForum();
    
    ForumEntity inputForum(String question_title, String question, String question_category, String question_level, String user_email);
}
